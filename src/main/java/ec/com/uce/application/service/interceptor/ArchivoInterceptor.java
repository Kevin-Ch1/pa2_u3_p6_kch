package ec.com.uce.application.service.interceptor;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Archivo
@Interceptor
@Priority(2)
public class ArchivoInterceptor {

    @AroundInvoke
    public Object crearArchivo(InvocationContext context) throws Exception {

        Object result = context.proceed();

        String nombreMetodo = context.getMethod().getName();
        String fecha = LocalDateTime.now().toString();

        try (FileWriter writer = new FileWriter("example.txt")) {
            writer.write("Nombre método: " + nombreMetodo + " \nFecha: " + fecha);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

}
