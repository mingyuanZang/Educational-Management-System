package application.controller;

import application.model.Programme;
import application.model.ProgrammeRegister;
import application.view.MultiOptionPaneProgramme;

public class MultiOptionPaneProgrammeController {
	
	private MultiOptionPaneProgramme view;
	private ProgrammeRegister programmeRegister;

	public MultiOptionPaneProgrammeController(ProgrammeRegister programmeRegister) {
		this.programmeRegister = programmeRegister;
	}

	public void addProgramme() {
		Programme newProgramme = FindProgrammetoAdd();
		if (newProgramme != null) {
			programmeRegister.adding(newProgramme);
			programmeRegister.addProgrammeToTable(newProgramme);
		} else {
			// programme was never added
		}
	}

	private Programme FindProgrammetoAdd() {
		this.view = new MultiOptionPaneProgramme();
		return view.getNewProgramme();
	}
	
}
