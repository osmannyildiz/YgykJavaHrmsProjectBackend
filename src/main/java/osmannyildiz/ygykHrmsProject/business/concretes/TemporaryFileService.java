package osmannyildiz.ygykHrmsProject.business.concretes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.ITemporaryFileService;

public class TemporaryFileService implements ITemporaryFileService {
	
//	protected String rootPath;
//	
//	public TemporaryFileService(String rootPath) {
//		this.rootPath = rootPath;
//	}

	@Override
	public DataResult<Path> storeAsTempFile(MultipartFile multipartFile) {
		Path tempFilePath;
		try {
			tempFilePath = Files.createTempFile(null, null);
			Files.copy(multipartFile.getInputStream(), tempFilePath, StandardCopyOption.REPLACE_EXISTING);
			return new SuccessDataResult<Path>(tempFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ErrorDataResult<Path>();
	}

}
