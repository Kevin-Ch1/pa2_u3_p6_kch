package ec.com.uce.application.service.interceptor;

import java.time.LocalDateTime;
import java.util.Arrays;

import ec.com.uce.application.service.AuditoriaService;
import ec.com.uce.domain.model.Auditoria;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Auditar
@Interceptor
public class AuditarInterceptor2 {

    @Inject
    private AuditoriaService auditoriaService;

    @AroundInvoke
    public Object auditar(InvocationContext context) throws Exception {

        String nombreMetodo = context.getMethod().getName();
        
        Object[] argumentos = context.getParameters();
        String argumentosStr = Arrays.toString(argumentos);

        System.out.println("Iniciando método: " + nombreMetodo + " | Argumentos: " + argumentosStr);

        long inicio = System.currentTimeMillis();

        Object resultado = context.proceed();

        long fin = System.currentTimeMillis();
        long tiempoTranscurrido = fin - inicio;

        System.out.println("Tiempo transcurrido: Método '" + nombreMetodo + "' con argumentos " + argumentosStr + " -> "
                + tiempoTranscurrido + " ms");

        Auditoria aud = new Auditoria();

        aud.setNombreMetodo(nombreMetodo);
        aud.setFechaHoraEjecucion(LocalDateTime.now());
        aud.setTiempoEjecucuionMS(tiempoTranscurrido);
        aud.setArgumentos(argumentosStr);

        this.auditoriaService.guardarAud(aud);
        
        return resultado;
    }
}
