/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.hsleiden.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
@Path("/")
public class DefaultResource
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get()
    {
        return "Hello World";
    }
}
