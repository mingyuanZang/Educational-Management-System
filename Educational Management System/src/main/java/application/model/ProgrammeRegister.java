package application.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import javax.swing.table.AbstractTableModel;

import application.IOStream.IOStream;

public class ProgrammeRegister extends AbstractTableModel {

	private HashSet<Programme> set = new HashSet<Programme>();
	private final String[] headers = {"Programme Name", "Programme Type"};

	public ProgrammeRegister() {
		if (IOStream.inputCollection("ProgrammeSetInProgrammeRegister.xml") != null) {
			set = (HashSet<Programme>) IOStream.inputCollection("ProgrammeSetInProgrammeRegister.xml");
		}
		else {
			set = new HashSet<Programme>();
		}
	}
	
	public ResponseMessage adding(Programme programme) {
		ResponseMessage response;
		
		if (programmeIsInRegister(programme)) {
			response = new ResponseMessage("Programme is already in the register");
		} else {
			set.add(programme);
			response = new ResponseMessage("Programme added");
		}
		return response;	
	}
	
	public void deleteProgramme(Programme programme) {
		set.remove(programme);
		fireTableDataChanged();
	}
	
	public Programme returnProgrammeByName(String programmeName) {
		Programme programme = null;
		for (Programme p : set) {
			if (p.getProgrammeName().equals(programmeName)) {
				programme = p;
			}
		}
		return programme;
	}

	public boolean programmeIsInRegister(Programme programme) {
		return set.contains(programme);
	}
	
	public HashSet<Programme> getSet() {
		return set;
	}

	public int getRowCount() {
		return set.size();
	}

	public int getColumnCount() {
		return headers.length;
	}

	public String getColumnName(int columnIndex) {
		return headers[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<Programme> programmeArrayList = new ArrayList<Programme>(set);
		switch (columnIndex) {
		case 0:
			return programmeArrayList.get(rowIndex).getProgrammeName();
		case 1:
			return programmeArrayList.get(rowIndex).getType();
		default:
			return null; // Must never happen
		}
	}

	public void addProgrammeToTable(Programme programme) {
		ArrayList<Programme> programmeArrayList = new ArrayList<Programme>(set);
		programmeArrayList.add(programme);
		fireTableRowsInserted(programmeArrayList.size() - 1, programmeArrayList.size() - 1);
	}
	
	public Programme returnProgrammeFromArrayList(int selectedRow) {
		ArrayList<Programme> programmeArrayList = new ArrayList<Programme>(set);
		return programmeArrayList.get(selectedRow);
	}

}


















