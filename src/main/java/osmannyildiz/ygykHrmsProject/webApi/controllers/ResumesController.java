package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import osmannyildiz.coreProject.business.abstracts.ICloudImageService;
import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.ErrorResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeEducationService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeExperienceService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeImageService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeLanguageService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeTechnologyService;
import osmannyildiz.ygykHrmsProject.business.abstracts.ITemporaryFileService;
import osmannyildiz.ygykHrmsProject.business.concretes.CloudinaryImageService;
import osmannyildiz.ygykHrmsProject.business.concretes.TemporaryFileService;
import osmannyildiz.ygykHrmsProject.business.constants.Messages;
import osmannyildiz.ygykHrmsProject.entities.concretes.Resume;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeEducation;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeExperience;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeImage;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeLanguage;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeTechnology;

@RestController
@RequestMapping("/api/resumes")
public class ResumesController {
	
	private IResumeService resumeService;
	private IResumeEducationService resumeEducationService;
	private IResumeExperienceService resumeExperienceService;
	private IResumeImageService resumeImageService;
	private IResumeLanguageService resumeLanguageService;
	private IResumeTechnologyService resumeTechnologyService;
	private ITemporaryFileService temporaryFileService;
	private ICloudImageService cloudImageService;
	
	@Autowired
	public ResumesController(
		IResumeService resumeService,
		IResumeEducationService resumeEducationService,
		IResumeExperienceService resumeExperienceService,
		IResumeImageService resumeImageService,
		IResumeLanguageService resumeLanguageService,
		IResumeTechnologyService resumeTechnologyService
	) {
		this.resumeService = resumeService;
		this.resumeEducationService = resumeEducationService;
		this.resumeExperienceService = resumeExperienceService;
		this.resumeImageService = resumeImageService;
		this.resumeLanguageService = resumeLanguageService;
		this.resumeTechnologyService = resumeTechnologyService;
		
		this.temporaryFileService = new TemporaryFileService();
		this.cloudImageService = new CloudinaryImageService();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<String, String>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>(errors, Messages.fixValidationErrors);
	}
	
	@GetMapping("/getById")
	public DataResult<Resume> getById(@RequestParam int id) {
		return new SuccessDataResult<Resume>(resumeService.getById(id).getData());
	}
	
	@PostMapping("/addResumeEducation")
	public Result addResumeEducation(@RequestBody @Valid ResumeEducation resumeEducation) {
		DataResult<ResumeEducation> result = resumeEducationService.add(resumeEducation);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/addResumeExperience")
	public Result addResumeExperience(@RequestBody @Valid ResumeExperience resumeExperience) {
		DataResult<ResumeExperience> result = resumeExperienceService.add(resumeExperience);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/addResumeImage")
	public Result addResumeImage(@RequestParam int resumeId, @RequestParam MultipartFile imageFile) {
		Path tempFilePath = temporaryFileService.storeAsTempFile(imageFile).getData();
		
		DataResult<String> uploadResult = cloudImageService.uploadImage(tempFilePath.toString());
		if (!uploadResult.isSuccess()) {
			return new ErrorResult(uploadResult.getMessage());
		}
		
		ResumeImage resumeImage = new ResumeImage();
		resumeImage.setImageUrl(uploadResult.getData());
		Resume resume = new Resume();
		resume.setJobSeekerUserId(resumeId);
		resumeImage.setResume(resume);
		
		DataResult<ResumeImage> addResult = resumeImageService.add(resumeImage);
		if (!addResult.isSuccess()) {
			return new ErrorResult(addResult.getMessage());
		}
		
		return new SuccessResult(addResult.getMessage());
	}
	
	@PostMapping("/addResumeLanguage")
	public Result addResumeLanguage(@RequestBody @Valid ResumeLanguage resumeLanguage) {
		DataResult<ResumeLanguage> result = resumeLanguageService.add(resumeLanguage);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/addResumeTechnology")
	public Result addResumeTechnology(@RequestBody @Valid ResumeTechnology resumeTechnology) {
		DataResult<ResumeTechnology> result = resumeTechnologyService.add(resumeTechnology);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/updateResume")
	public Result updateResume(@RequestBody @Valid Resume resume) {
		DataResult<Resume> result = resumeService.update(resume);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/updateResumeEducation")
	public Result updateResumeEducation(@RequestBody @Valid ResumeEducation resumeEducation) {
		DataResult<ResumeEducation> result = resumeEducationService.update(resumeEducation);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/updateResumeExperience")
	public Result updateResumeExperience(@RequestBody @Valid ResumeExperience resumeExperience) {
		DataResult<ResumeExperience> result = resumeExperienceService.update(resumeExperience);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/updateResumeLanguage")
	public Result updateResumeLanguage(@RequestBody @Valid ResumeLanguage resumeLanguage) {
		DataResult<ResumeLanguage> result = resumeLanguageService.update(resumeLanguage);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/updateResumeTechnology")
	public Result updateResumeTechnology(@RequestBody @Valid ResumeTechnology resumeTechnology) {
		DataResult<ResumeTechnology> result = resumeTechnologyService.update(resumeTechnology);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/deleteResumeEducation")
	public Result deleteResumeEducation(@RequestBody @Valid ResumeEducation resumeEducation) {
		Result result = resumeEducationService.delete(resumeEducation);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/deleteResumeExperience")
	public Result deleteResumeExperience(@RequestBody @Valid ResumeExperience resumeExperience) {
		Result result = resumeExperienceService.delete(resumeExperience);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/deleteResumeImage")
	public Result deleteResumeImage(@RequestBody @Valid ResumeImage resumeImage) {
		Result result = resumeImageService.delete(resumeImage);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/deleteResumeLanguage")
	public Result deleteResumeLanguage(@RequestBody @Valid ResumeLanguage resumeLanguage) {
		Result result = resumeLanguageService.delete(resumeLanguage);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@PostMapping("/deleteResumeTechnology")
	public Result deleteResumeTechnology(@RequestBody @Valid ResumeTechnology resumeTechnology) {
		Result result = resumeTechnologyService.delete(resumeTechnology);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}

}
