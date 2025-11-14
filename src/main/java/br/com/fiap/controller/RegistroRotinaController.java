package br.com.fiap.controller;

import br.com.fiap.dto.FeedbackCheckOutDTO; // Importe o DTO
import br.com.fiap.model.RegistroRotina;
import br.com.fiap.service.RegistroRotinaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/registros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistroRotinaController {

    @Inject
    private RegistroRotinaService registroRotinaService;

    @POST
    public Response realizarCheckOut(RegistroRotina registro) {
        FeedbackCheckOutDTO feedback = registroRotinaService.realizarCheckOut(registro);

        return Response.status(Response.Status.CREATED).entity(feedback).build();
    }

    @GET
    public Response getAll() {
        List<RegistroRotina> registros = registroRotinaService.getAll();
        return Response.ok(registros).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        RegistroRotina r = registroRotinaService.getById(id);
        return Response.ok(r).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        registroRotinaService.delete(id);
        return Response.noContent().build();
    }
}