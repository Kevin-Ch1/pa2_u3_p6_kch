package ec.com.uce.application.service.interceptor;

import java.util.Arrays;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@MedirTiempo
@Interceptor
public class MedirTiempoInterceptor {

    @AroundInvoke
    public Object medir(InvocationContext context) throws Exception {

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

        return resultado;
    }
}
