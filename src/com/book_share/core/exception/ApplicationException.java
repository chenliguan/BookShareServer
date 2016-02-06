
package com.book_share.core.exception;

public class ApplicationException extends RuntimeException
{   
    private String message;
    private String messageDetails;


    /**
     * Default constructor. Takes no arguments.
     */
    public ApplicationException()
    {
        super();
    }

    /**
     * Constructor.
     *
     * @param message
     */
    public ApplicationException(String message)
    {
        super(message);
        
        this.message = message;
    }

    /**
     * Constructor
     * 
     * @param message
     * @param messageDetails
     */
    public ApplicationException(String message, String messageDetails)
    {
        super(message);
        
        this.message = message;
        this.messageDetails = messageDetails;
    }


    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @return the messageDetails
     */
    public String getMessageDetails()
    {
        return messageDetails;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @param messageDetails the messageDetails to set
     */
    public void setMessageDetails(String messageDetails)
    {
        this.messageDetails = messageDetails;
    }
}
