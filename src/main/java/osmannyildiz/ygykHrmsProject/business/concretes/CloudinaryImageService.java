package osmannyildiz.ygykHrmsProject.business.concretes;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import osmannyildiz.coreProject.business.abstracts.ICloudImageService;
import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.constants.Secrets;

public class CloudinaryImageService implements ICloudImageService {
	
	protected Cloudinary cloudinary;
	
	public CloudinaryImageService() {
		cloudinary = new Cloudinary(ObjectUtils.asMap(
			"cloud_name", Secrets.cloudinaryCloudName,
			"api_key", Secrets.cloudinaryApiKey,
			"api_secret", Secrets.cloudinaryApiSecret 
		));
	}

	@Override
	public DataResult<String> uploadImage(String imageFilePath) {
		File file = new File(imageFilePath);
		try {
			Map<String, String> response = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			String imageUrl = response.getOrDefault("secure_url", null);
			if (imageUrl ==  null || imageUrl.isEmpty()) {
				return new ErrorDataResult<String>(null, "Resim sunucuya yüklenemedi.");
			}
			return new SuccessDataResult<String>(imageUrl, "Resim başarıyla yüklendi.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ErrorDataResult<String>(null, "Resim sunucuya yüklenemedi.");
	}

}
