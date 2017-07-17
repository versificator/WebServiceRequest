package cloud_task;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.codec.binary.Base64;

/* 
 * Can replace this with any base 64 encoder for basic authentication. For java 6 installations on Sun's JRE you can use "sun.misc.BASE64Encoder" 
 * however this will not work in some installations (using OpenJDK). Java mail (javax.mail.internet.MimeUtility) also contains a Base 64 encoder in Java 6. 
 * A public domain version exists at http://iharder.sourceforge.net/current/java/base64/
 */

/**
 * This is a stub class with a main method to run a Remote Manager web service.
 */
public class WebServiceRequest {

    public static void main(String[] args) throws JAXBException {
        try {
            // Create url to Remote Manager server for a given web service
            // request
            URL url = new URL("http://login.etherios.com/ws/DataStream");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            // replace with your username/password
            String userpassword = "nanri-test:!Nanri0118";
            // can change this to use a different base64 encoder
            String encodedAuthorization = Base64.encodeBase64String(
                    userpassword.getBytes()).trim();
            conn.setRequestProperty("Authorization", "Basic "
                    + encodedAuthorization);
            // Send data to server
            conn.setRequestProperty("Content-Type", "text/xml");
            // Get input stream from response and convert to String
            conn.disconnect();
            conn.setDoInput(true);
            InputStream is = conn.getInputStream();
            Scanner isScanner = new Scanner(is);
            StringBuffer buf = new StringBuffer();
            while (isScanner.hasNextLine()) {
                buf.append(isScanner.nextLine() + "\n");
            }
            // Output response to standard out
            String responseContent = buf.toString();
            // System.out.println(responseContent);

            StringReader sr = new StringReader(responseContent);
            JAXBContext jaxbContext = JAXBContext.newInstance(Result.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Result machine = (Result) jaxbUnmarshaller.unmarshal(sr);
            System.out.println(machine);

        } catch (IOException e) {
            // Print any exceptions that occur
            e.printStackTrace();
        }
    }

    private static class CurrentValue {
        private String id;
        private int timestamp;
        private String timestampISO;
        private int serverTimestamp;
        private String serverTimestampISO;
        private String data;
        private String dataFormat;
        private String description;
        private int quality;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getTimestampISO() {
            return timestampISO;
        }

        public void setTimestampISO(String timestampISO) {
            this.timestampISO = timestampISO;
        }

        public int getServerTimestamp() {
            return serverTimestamp;
        }

        public void setServerTimestamp(int serverTimestamp) {
            this.serverTimestamp = serverTimestamp;
        }

        public String getServerTimestampISO() {
            return serverTimestampISO;
        }

        public void setServerTimestampISO(String serverTimestampISO) {
            this.serverTimestampISO = serverTimestampISO;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getDataFormat() {
            return dataFormat;
        }

        public void setDataFormat(String dataFormat) {
            this.dataFormat = dataFormat;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getQuality() {
            return quality;
        }

        public void setQuality(int quality) {
            this.quality = quality;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("   id: " + id + "\n" + "timestamp: " + timestamp
                    + "\n" + "data: " + data + "\n" + "dataFormat: "
                    + dataFormat);
            return buffer.toString();
        }
    }

    private static class DataStream {
        private int cstId;
        private String streamId;
        private String dataType;
        private String forwardTo;
        private CurrentValue currentValue;
        private String description;
        private int units;
        private int dataTtl;
        private int rollupTtl;

        public DataStream(int cstId, String streamIdl, String dataType,
                String forwardTo, CurrentValue currentValue,
                String description, int units, int dataTtl, int rollupTtl) {

            this.cstId = cstId;
            this.streamId = streamId;
            this.dataType = dataType;
            this.forwardTo = forwardTo;
            this.currentValue = currentValue;
            this.description = description;
            this.units = units;
            this.dataTtl = dataTtl;
            this.rollupTtl = rollupTtl;
        }

        public DataStream() {
        }

        public int getCstId() {
            return cstId;
        }

        public void setCstId(int cstId) {
            this.cstId = cstId;
        }

        public String getStreamIdl() {
            return streamId;
        }

        public void setStreamIdl(String streamIdl) {
            this.streamId = streamIdl;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getForwardTo() {
            return forwardTo;
        }

        public void setForwardTo(String forwardTo) {
            this.forwardTo = forwardTo;
        }

        public CurrentValue getCurrentValue() {
            return currentValue;
        }

        public void setCurrentValue(CurrentValue currentValue) {
            this.currentValue = currentValue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getUnits() {
            return units;
        }

        public void setUnits(int units) {
            this.units = units;
        }

        public int getDataTtl() {
            return dataTtl;
        }

        public void setDataTtl(int dataTtl) {
            this.dataTtl = dataTtl;
        }

        public int getRollupTtl() {
            return rollupTtl;
        }

        public void setRollupTtl(int rollupTtl) {
            this.rollupTtl = rollupTtl;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("\n" + "============================" + "\n"
                    + "cstId: "
                    + cstId
                    + "\n"
                    + "streamId: "
                    + streamId
                    + "\n"
                    + "dataType: "
                    + dataType
                    + "\n"
                    + "forwardTo: "
                    + forwardTo
                    + "\n"
                    + "currentValue: "
                    + currentValue
                    + "\n"
                    + "description: "
                    + description
                    + "\n"
                    + "units: "
                    + units
                    + "\n"
                    + "dataTtl: "
                    + dataTtl
                    + "\n"
                    + "rollupTtl: "
                    + rollupTtl + "\n");
            return buffer.toString();

        }
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Result {
        private int resultSize;
        private int requestedSize;
        private String pageCursor;

        public Result(int resultSize, int requestedSize, String pageCursor,
                List<DataStream> dataStream) {

            this.resultSize = resultSize;
            this.requestedSize = requestedSize;
            this.pageCursor = pageCursor;
            this.dataStream = dataStream;
        }

        public Result() {
        }

        @XmlElement(name = "DataStream")
        private List<DataStream> dataStream = new ArrayList<DataStream>();

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("resultSize: " + resultSize + "\n");
            buffer.append("dataStream: \n");
            buffer.append(dataStream);
            return buffer.toString();
        }

    }
}
