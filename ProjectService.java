package project.service;

import project.dao.ProjectDao;
import project.entity.Projects;


public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();

	public Projects addProject(Projects project) {
		return projectDao.insertProject(project);
	}

	}
