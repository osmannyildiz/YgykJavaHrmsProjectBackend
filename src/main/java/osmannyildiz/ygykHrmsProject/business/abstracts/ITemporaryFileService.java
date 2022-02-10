package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import osmannyildiz.coreProject.utilities.results.DataResult;

public interface ITemporaryFileService {
	
	DataResult<Path> storeAsTempFile(MultipartFile multipartFile);

}
