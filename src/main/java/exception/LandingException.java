package exception;

/**
 * Created by thibs911hotmail.com on 06/11/2015.
 */

/**
 * Exception custom qui remonte en erreur quand la procédure d'extraction ou de rétractation rencontre un problème
 */
public class LandingException extends Exception {

    public LandingException(String message){
        super(message);
    }
}
