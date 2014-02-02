// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <h1>Command "agencyList"</h1>
 * <p>
 * To obtain a list of available agencies the following command should be used:
 * <p>
 * <a href=
 * "http://webservices.nextbus.com/service/publicXMLFeed?command=agencyList"
 * >http://webservices.nextbus.com/service/publicXMLFeed?command=agencyList</a>
 * <p>
 * The resulting XML will be in the form (note: the shortTitle element is
 * provided only if it is different than the standard title element. If no
 * shortTitle element is provided, simply use the standard title element):
 * <p>
 * 
 * <pre>
 * {@code
 * <body>
 *     <agency tag="actransit" title="AC Transit, CA" regionTitle="California-Northern" />
 *     <agency tag="alexandria" title="Alexandria DASH, VA" shortTitle="DASH" regionTitle="Virginia" />
 *     <agency tag="amerimar" title="Amerimar" regionTitle="Pennsylvania" />
 *     <agency tag="blackhawk" title="Black Hawk Transportation Authority, CO" shortTitle="Black Hawk" regionTitle="Colorado" />
 *     <agency tag="camarillo" title="Camarillo Area (CAT), CA" shortTitle="Camarillo (CAT)" regionTitle="California-Southern" />
 *     <agency tag="case-western" title="Case Western University, OH" shortTitle="Case Western" regionTitle="Ohio" />
 *     <agency tag="chapel-hill" title="Chapel Hill Transit, NC" shortTitle="Chapel Hill" regionTitle="North Carolina" />
 *     <!-- ... -->
 * </body>
 * }
 * </pre>
 * <p>
 * Note: For all commands below an agency tag, “a” should be included in the
 * query string and set to an agency name such as sf-muni (e.g., a=sf-muni).
 * 
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType
public class AgencyList {

    private List<Agency> agencies;
    private String copyright;
    private Error error;

    @XmlElement(name = "agency")
    public List<Agency> getAgencies() {
        return agencies;
    }

    @XmlAttribute
    public String getCopyright() {
        return copyright;
    }

    @XmlElement(name = "Error")
    public Error getError() {
        return error;
    }

    @SuppressWarnings("unused")
    private void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    @SuppressWarnings("unused")
    private void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @SuppressWarnings("unused")
    private void setError(Error error) {
        this.error = error;
    }

    @XmlType
    public static class Agency {

        private String regionTitle;
        private String shortTitle;
        private String tag;
        private String title;

        @XmlAttribute
        public String getRegionTitle() {
            return regionTitle;
        }

        /**
         * The shortTitle element is provided only if it is different than the
         * standard title element. If no shortTitle element is provided, simply
         * use the standard title element
         */
        @XmlAttribute
        public String getShortTitle() {
            return shortTitle;
        }

        @XmlAttribute
        public String getTag() {
            return tag;
        }

        @XmlAttribute
        public String getTitle() {
            return title;
        }

        @SuppressWarnings("unused")
        private void setRegionTitle(String regionTitle) {
            this.regionTitle = regionTitle;
        }

        @SuppressWarnings("unused")
        private void setShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
        }

        @SuppressWarnings("unused")
        private void setTag(String tag) {
            this.tag = tag;
        }

        @SuppressWarnings("unused")
        private void setTitle(String title) {
            this.title = title;
        }
    }
}
