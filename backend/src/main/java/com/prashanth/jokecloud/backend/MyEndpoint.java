/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.prashanth.jokecloud.backend;
import com.prashanth.Joke;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.jokecloud.prashanth.com",
    ownerName = "backend.jokecloud.prashanth.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a free joke from javalib */
    @ApiMethod(name = "getJokeFromLib")
    public MyBean getJokeFromLibrary() {
        Joke joke = new Joke();
        MyBean response = new MyBean();
        response.setData(joke.getJoke());
        return response;
    }

}
