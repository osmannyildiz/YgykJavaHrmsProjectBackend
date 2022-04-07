package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeImageService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IResumeImageDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeImage;

@Service
public class ResumeImageManager implements IResumeImageService {
	
	private IResumeImageDao resumeImageDao;

	@Autowired
	public ResumeImageManager(IResumeImageDao resumeImageDao) {
		this.resumeImageDao = resumeImageDao;
	}

	@Override
	public DataResult<List<ResumeImage>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<ResumeImage>>(resumeImageDao.getAllByResumeId(resumeId));
	}

	@Override
	public DataResult<ResumeImage> add(ResumeImage resumeImage) {
		return new SuccessDataResult<ResumeImage>(resumeImageDao.save(resumeImage));
	}

	@Override
	public DataResult<ResumeImage> update(ResumeImage resumeImage) {
		return new SuccessDataResult<ResumeImage>(resumeImageDao.save(resumeImage));
	}

	@Override
	public Result delete(ResumeImage resumeImage) {
		resumeImageDao.delete(resumeImage);
		return new SuccessResult();
	}
	
}
