package reivax.norac.interviewprep.webapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Helper class for file upload.
 * 
 * @author Xavier
 *
 */
public class UploadJsonFile {

	/**
	 * Method #1 to upload on the file system (JEE version)
	 * 
	 * @param filePath path to the file
	 * @param fileName name of the file
	 * @param filePart uploaded content
	 * @throws IOException
	 */
	public static void upload(String filePath, String fileName, Part filePart) throws IOException{

		OutputStream out = null;
		InputStream filecontent = null;

		try {
			// Create file on the system (temporary)
			out = new FileOutputStream(new File(filePath + File.separator
					+ fileName));
			
			// Get the upload
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			// Browse content
			while ((read = filecontent.read(bytes)) != -1) {
				// Write the file on the system (temporary)
				out.write(bytes, 0, read);
			}
		} catch (FileNotFoundException fne) {
			System.err.println("You either did not specify a file to upload or are "
					+ "trying to upload a file to a protected or nonexistent "
					+ "location.");
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
		}
	}

	/**
	 * Method #2 to upload on the file system (Apache Commons version)
	 * 
	 * @param request the request containing the upload 
	 * @param filePath path to the file
	 * @param fileName name of the file
	 * @throws Exception
	 */
	public static void upload2(HttpServletRequest request, String filePath, String fileName) throws Exception{
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File file;

		// Get the upload
		ServletFileUpload upload = new ServletFileUpload(factory);

		try{ 
			// Browse files
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();

			while ( i.hasNext () ) 
			{
				FileItem fi = (FileItem)i.next();
				if ( !fi.isFormField () )	
				{
					// Write the file on the system (temporary)
					file = new File(filePath + File.separator
							+ fileName) ;
					fi.write( file ) ;
				}
			}
		}catch(FileNotFoundException ex) {
			System.out.println(ex);
		}
	}
}
