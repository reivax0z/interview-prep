package reivax.norac.interviewprep.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

public class DownloadJsonFile {

	/**
	 * Downloads the content of the book DB as a JSON file.
	 * 
	 * @param jsonAsString the content of the DB as a valid JSON String
	 * @param response the HttpServletResponse
	 * @param filePath the path to the file for temp storage
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public void download(HttpServletResponse response, String filePath) throws IOException, URISyntaxException{
		String fileName = "sample.json";
        String fileType = "json";

		URL resource = this.getClass().getResource(filePath + File.separator
				+ fileName);
		File my_file = Paths.get(resource.toURI()).toFile();
        response.setContentType(fileType);

        response.setHeader("Content-disposition","attachment; filename="+fileName);

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
           out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
	}
}
