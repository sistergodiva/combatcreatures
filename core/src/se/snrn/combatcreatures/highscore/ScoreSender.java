package se.snrn.combatcreatures.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;

import java.util.HashMap;

public class ScoreSender {


    private Score score;
    private String url;
    HttpRequestBuilder requestBuilder;
    Net.HttpResponseListener httpResponseListener;


    public ScoreSender(String url) {

        this.url = url;

        httpResponseListener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        };

        requestBuilder = new HttpRequestBuilder();

    }

    public void sendScore(Score score) {



        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("n", score.getNamn());
        parameters.put("s", String.valueOf(score.getScore()));
        parameters.put("l", String.valueOf(score.getLevel()));
        parameters.put("f", String.valueOf(score.getFloor()));
        parameters.put("e", score.getEndTime());
        parameters.put("b", score.getStartTime());


        Net.HttpRequest httpRequest = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url(url)
                .content(HttpParametersUtils.convertHttpParameters(parameters))
                .build();
        System.out.println(httpRequest.getContent());
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);

    }

}
