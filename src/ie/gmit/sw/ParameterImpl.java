package ie.gmit.sw;

/**
 * Created by Ross Byrne on 23/01/17.
 */
public class ParameterImpl implements Parameterable {

    private Object parameter;
    private ParameterType parameterType;

    public Object getParameter() {
        return parameter;
    }

    public boolean setParameter(Object parameter, ParameterType type) {

        // make sure the variable given is actually the type specified

        try {

            switch (type){
                case NUMBER:
                    // try parse to number
                    Double.parseDouble(parameter.toString());
                    break;
                case OPERATOR:
                    // do something with operator

                    if( parameter.toString() == ">" ||
                        parameter.toString() == "<" ||
                        parameter.toString() == "==" ||
                        parameter.toString() == "AND" ||
                        parameter.toString() == "OR"
                    ){
                        // ok, save them
                    } else {
                        // not ok, return
                        return false;
                    } // if

                    break;
                case EXPRESSION:

                    // make sure the object is a parameter
                    if(parameter instanceof Expressionable){

                        // save object
                    } else {

                        return false;
                    }
                    break;
            } // switch
            // if it hasn't crashed by now, save values

            // save type
            setParameterType(type);

            // save parameter
            this.parameter = parameter;

            // operation successful
            return true;

        } catch(Exception e){

            // type of value is not correct
            // operation failed
            return false;

        } // try
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    private void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }

} // class
