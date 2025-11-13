package config;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que possui as constantes de configuração do projeto
 */
public final class Config {
    
    public static final String JDBC_DRIVER="org.postgresql.Driver";
    public static final String JDBC_URL="jdbc:postgresql://localhost:5432/Ecommerce";
    public static final String JDBC_USUARIO="postgres";
    public static final String JDBC_SENHA="postgres";
    
    private Config() {
        
    }
    
}
