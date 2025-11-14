package br.com.fiap.controller;

import br.com.fiap.dto.DashboardStatsDTO;
import br.com.fiap.service.DashboardService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardController {

    @Inject
    private DashboardService dashboardService;

    @GET
    @Path("/stats") // Endpoint: GET /api/dashboard/stats
    public Response getStats() {
        // Busca os dados agregados e anônimos
        DashboardStatsDTO stats = dashboardService.getStats();
        return Response.ok(stats).build();
    }
}