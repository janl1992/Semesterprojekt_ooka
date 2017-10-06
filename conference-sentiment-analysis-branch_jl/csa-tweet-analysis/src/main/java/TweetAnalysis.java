import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.japi.Pair;
import akka.japi.function.Function;
import akka.kafka.ConsumerSettings;
import akka.kafka.Subscriptions;
import akka.kafka.javadsl.Consumer;
import akka.stream.*;
import akka.stream.javadsl.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.csa.tweet.collector.Tweet;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by janloeffelsender on 30.03.17.
 */
public class TweetAnalysis {
    private static final Logger log = LoggerFactory.getLogger( TweetAnalysis.class );

    private static TweetPrint resulttweet;

    public static void main(String args[]) throws InterruptedException, TimeoutException, ExecutionException {
        final Function<Throwable, Supervision.Directive> decider = ex -> {
            log.error( "Error during stream processing.", ex );
            return Supervision.stop();
        };
        // actor system and materializer always needed to use Akka Streams
        final ActorSystem system = ActorSystem.create();
        final Materializer materializer = ActorMaterializer.create(ActorMaterializerSettings.create(system)
                .withSupervisionStrategy(decider), system);
        // this decider applies the default supervision strategy, but it adds some logging in case of an error



        final ConsumerSettings<String, String> consumerSettings = ConsumerSettings.create( system, new StringDeserializer(), new StringDeserializer() )
//				.withBootstrapServers("192.168.1.24:19092")
                .withBootstrapServers( "0.0.0.0:19092" )
                .withGroupId( "analyser1" )
                .withProperty( ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest" )
                .withProperty( ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false" );

        final Source<String, Consumer.Control> sourcetweet;
        {
            sourcetweet = Consumer.committableSource( consumerSettings, Subscriptions.topics( "tweet-topic" ) ).map( cm -> cm.record().value() );
        }

        final Flow<String, Pair<List<Tweet>, Integer>, NotUsed> tweetmapping_flow;
        {
            Flow<String, Pair<List<Tweet>, Integer>, NotUsed> flow =
                    Flow.fromFunction( p -> {
                        System.out.println("tweetmapping_flow");
                        JsonParser parser = new JsonParser();
                        JsonObject object = (JsonObject) parser.parse( p );
                        int id = object.get( "id" ).getAsInt();
                        List<Tweet> tweetlist = new ArrayList<>();
                        String current_tweet = "";
                        int i = 0;
                        while (current_tweet != null) {
                            try {
                                current_tweet = object.get( "tweet" + i ).getAsString();
                                tweetlist.add( new Tweet( current_tweet ) );
                                i++;
                            } catch (NullPointerException e) {
                                current_tweet = null;
                            }
                        }

                        return Pair.create( tweetlist, id );
                    } );
            tweetmapping_flow = flow.log( "tweetmapping_flow log" );
        }

        final Flow <Pair<List<Tweet>, Integer>, TweetAnalyzed, NotUsed> tweetratingflow;
        {
            Flow<Pair<List<Tweet>, Integer>, TweetAnalyzed, NotUsed> flow =
                    Flow.fromFunction(p -> {
                        TweetAnalyzed tweetanalysis = new TweetAnalyzed(p.second());
                        TweetCloudAnalysis tweetcloud = new TweetCloudAnalysis();
                        for(Tweet tweet : p.first())
                        {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            tweet = tweetcloud.analyzed_tweet( tweet );
                            tweetanalysis.addtweet( tweet );
                        }
                        return tweetanalysis;
                    } );
            tweetratingflow = flow.log("csa-tweetratingflow");
        }

        Flow<TweetAnalyzed,NotUsed,NotUsed> AnalyszedTweetResult;
        {
            Flow<TweetAnalyzed, NotUsed, NotUsed> flow =
                    Flow.fromFunction(p -> {
                        if(resulttweet == null) {
                            resulttweet = new TweetPrint();
                        }
                        resulttweet.add_result_tweet_list( p );
                        return NotUsed.getInstance();
                    });
            AnalyszedTweetResult = flow.log( "AnalyszedTweetResult" );
        }


        final Sink<NotUsed, CompletionStage<Done>> sink = Sink.foreach( p -> {
            resulttweet.print_tweet_result();
        } );

        final Graph<ClosedShape, CompletionStage<Done>> g = GraphDSL.create( sink, (b, s) -> {
            final SourceShape<String> sourcetweetshape = b.add( sourcetweet );

            final FlowShape<String, Pair<List<Tweet>, Integer>> tweetmapflowshape = b.add( tweetmapping_flow );

            final FlowShape<Pair<List<Tweet>, Integer>, TweetAnalyzed> tweetratingflowshape = b.add(tweetratingflow);

            final FlowShape<TweetAnalyzed,NotUsed> AnalyszedTweetResultshape = b.add( AnalyszedTweetResult );

            b.from( sourcetweetshape )
                    .via( tweetmapflowshape )
                    .via( tweetratingflowshape )
                    .via(AnalyszedTweetResultshape)
                    .to(s);

                    return ClosedShape.getInstance();
        });
// run it and check the status
        CompletionStage<Done> completionStage = RunnableGraph.fromGraph( g ).run( materializer );
        CompletableFuture<Done> completableFuture = completionStage.toCompletableFuture();
        while (!completableFuture.isDone()) {

        }

        // log the overall status
        if (completableFuture.isDone()) {
            log.info( "Done." );
        }
        if (completableFuture.isCompletedExceptionally()) {
            log.warn( "Completed exceptionally." );
        }
        if (completableFuture.isCancelled()) {
            log.warn( "Canceled." );
        }

        // trigger graceful shutdown of the actor system
        system.shutdown();
        Thread.sleep( 500 );

    }


}
