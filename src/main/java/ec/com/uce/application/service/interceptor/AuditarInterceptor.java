package ec.com.uce.application.service.interceptor;

import java.time.LocalDateTime;

import ec.com.uce.application.service.AuditoriaService;
import ec.com.uce.domain.model.Auditoria;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@AuditarI
@Interceptor
public class AuditarInterceptor {

    @Inject
    private AuditoriaService auditoriaService;

    @AroundInvoke
    public Object registrar(InvocationContext context) throws Exception {
        Auditoria a = new Auditoria();

        Long inicio = System.currentTimeMillis();

        Object registrar = context.proceed();
        Object[] obj = context.getParameters();
        a.setNombreMetodo(context.getMethod().getName());
        a.setArgumentos(obj.getClass().getTypeParameters().toString());
        a.setFechaHoraEjecucion(LocalDateTime.now());
        Long fin = System.currentTimeMillis() - inicio;

        a.setTiempoEjecucuionMS(fin);

        auditoriaService.guardar(a);

        return registrar;
    }

}
