package com.logibear.api;

import static spark.Spark.get;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * <p>Can open new endpoints and provides default
 * methods to render json outputs.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public abstract class Endpoint implements Route {

    /**
     * <p>Default json structure for output.</p>
     * @author Stephan Strate
     * @since 1.0.0
     */
    class Message {

        Status status;
        String message;

        /**
         * <p>Create a new message with a status and
         * a message (can be a json string).</p>
         * @param status    status code and message
         * @param message   output message
         */
        Message (Status status, String message) {
            this.status = status;
            this.message = message;
        }
    }

    /**
     * <p>Status code and message in json
     * structure.</p>
     * @author Stephan Strate
     * @since 1.0.0
     */
    class Status {

        int code;
        String message;

        /**
         * <p>Default constructor to add status
         * code later.</p>
         */
        Status () {

        }

        /**
         * <p>Special constructor to create
         * custom status objects.</p>
         * @param code      status code
         * @param message   status message
         */
        Status (int code, String message) {
            this.code = code;
            this.message = message;
        }

        /**
         * <p>Returns status code.</p>
         * @return  status code
         */
        int getCode() {
            return code;
        }

        /**
         * <p>Returns status message.</p>
         * @return  status message
         */
        String getMessage() {
            return message;
        }

        /**
         * <p>Sets status code.</p>
         * @param code  status code
         * @return  status code
         */
        int setCode (int code) {
            switch (code) {
                case 200: {
                    setMessage("Ok");
                    return this.code = 200;
                }

                case 404: {
                    setMessage("Not Found");
                    return this.code = 404;
                }

                default: {
                    setMessage("Internal Server Error");
                    return this.code = 500;
                }
            }
        }

        /**
         * <p>Sets status message.</p>
         * @param message   status message
         */
        void setMessage (String message) {
            this.message = message;
        }
    }

    /**
     * <p>Opening the endpoint.</p>
     * @param endpoint  endpoint
     */
    public Endpoint (String endpoint) {
        get(endpoint, this::handle);
    }

    /**
     * <p>Render json output with status code
     * and message.</p>
     * @param code      status code
     * @param message   custom message (response)
     * @param response  response object
     * @return  json string
     */
    protected String render (int code, String message, Response response) {
        // define response header (always json)
        response.type("application/json");

        // status code incl. message
        Status status = new Status();
        response.status(status.setCode(code));

        // custom error message incl. status
        Message renderMessage = new Message(status, message);

        // parse to json
        Gson gson = new Gson();
        return gson.toJson(renderMessage);
    }

    /**
     * <p>Render a default error.</p>
     * @param response  response object
     * @return  json string
     */
    protected String error (Response response) {
        return render(500, "Undefined error.", response);
    }

    /**
     * <p>Default api handler. Renders a default error,
     * can be overwritten.</p>
     * @param request   request object
     * @param response  response object
     * @return  json string
     * @throws Exception default
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return error(response);
    }
}