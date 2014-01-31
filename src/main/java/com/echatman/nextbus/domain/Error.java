// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <h1>Error Messages</h1>
 * <p>
 * Commands can return an Error XML object if there is some type of problem such
 * as the system has not been initialized yet or bad parameters were used in the
 * URL. When an Error XML object is returned, it has an attribute called
 * shouldRetry. If the error was returned only because the server was
 * initializing, then shouldRetry will be set to true. For this case the
 * application should try the URL again after waiting 10 seconds. If shouldRetry
 * attribute is set to false though, that means that there is an error due to
 * the URL and simply retrying the URL again will not fix the problem.
 * 
 * <p>
 * An error message looks like:
 * 
 * <pre>
 * &lt;body>
 *     &lt;Error shouldRetry="true">
 *     Agency server cannot accept client while status is: agency
 *     name = sf-muni,status = UNINITIALIZED, client count = 0, last
 *     even t = 0 seconds ago Could not get route list for agency tag "sf-muni".
 *     Either the route tag is bad or the system is initializing.
 *     &lt;/Error>
 * &lt;/body>
 * </pre>
 * 
 * @author echatman
 */
@XmlType
public class Error {
    @XmlAttribute
    Boolean shouldRetry;
    @XmlValue
    String errorMessage;
}
