package mk.ukim.finki.nsi.dms.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import mk.ukim.finki.nsi.dms.model.Doctor;
import mk.ukim.finki.nsi.dms.model.Measure;
import mk.ukim.finki.nsi.dms.service.DoctorService;
import mk.ukim.finki.nsi.dms.service.MeasureService;
import mk.ukim.finki.nsi.dms.service.PatientService;

@Controller
public class DmsController {

	@Autowired
	DoctorService doctorService;

	@Autowired
	PatientService patientService;

	@Autowired
	MeasureService measureService;

	@RequestMapping(value = "/")
	public ModelAndView root() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(@RequestParam String username, @RequestParam String password, HttpSession session) {

		Doctor doctor = new Doctor();
		doctor.setUsername(username);
		doctor.setPassword(password);
		ModelAndView result;
		if (!doctorService.signInDoctor(doctor)) {
			result = new ModelAndView(new RedirectView("login"));
			result.addObject("message", "Not logged in.");
		} else {
			result = new ModelAndView(new RedirectView("home"));
			result.addObject("doctor", doctor);
			session.setAttribute("username", username);
		}
		return result;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
		return new ModelAndView("signup");
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signupPost(HttpSession httpSession, HttpServletRequest request, @RequestParam String username,
			@RequestParam String password, @RequestParam String name) {

		ModelAndView result;
		if (doctorService.findDoctorByUsername(username) == null) {
			Doctor doctor = new Doctor(username, password, name);
			doctorService.addDoctor(doctor);

			result = new ModelAndView("login");
			result.addObject("message", "User successfully signed up. Please sign in");
		} else {
			result = new ModelAndView("signup");
			result.addObject("message", "Specified username exists, please select another one.");
		}

		return result;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpSession httpSession) {

		if (null == httpSession.getAttribute("username") || "".equals(httpSession.getAttribute("username"))) {
			return new ModelAndView(new RedirectView("login"));
		}
		ModelAndView result = new ModelAndView("home");
		Doctor doctor = doctorService.findDoctorByUsername(httpSession.getAttribute("username").toString());
		result.addObject("patients", doctorService.getAllPatientsByDoctorId(doctor.getId()));
		return result;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView signOut(HttpSession session) {
		session.removeAttribute("username");
		return new ModelAndView(new RedirectView("login"));
	}

	@RequestMapping(value = "/delete/patient", method = RequestMethod.GET)
	public ModelAndView deletePatient(@RequestParam int id, HttpSession httpSession) {
		patientService.removeDoctorFromPatient(id);
		ModelAndView result = new ModelAndView("redirect:/home");
		Doctor doctor = doctorService.findDoctorByUsername(httpSession.getAttribute("username").toString());
		result.addObject("patients", doctorService.getAllPatientsByDoctorId(doctor.getId()));
		return result;
	}

	@RequestMapping(value = "/patient", method = RequestMethod.GET)
	public ModelAndView viewPatient(@RequestParam int id, HttpSession httpSession) throws Exception {

		if (null == httpSession.getAttribute("username") || "".equals(httpSession.getAttribute("username"))) {
			return new ModelAndView(new RedirectView("login"));
		}

		ModelAndView result = new ModelAndView("patient");
		result.addObject("patient", patientService.getPatient(id));
		List<Measure> totalMeasures = measureService.getAllMeasuresByPatientId(id);
		List<Integer> measuresArray = new ArrayList<Integer>();
		List<String> measureDates = new ArrayList<String>();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy hh:mm");
		for (int i = 0; i < totalMeasures.size(); i++) {
			measuresArray.add(totalMeasures.get(i).getLevel());
			measureDates.add("'" + formatter.format(totalMeasures.get(i).getDateAdded()) + "'");
		}
		result.addObject("measures", measuresArray);
		result.addObject("measureDates", measureDates);
		return result;
	}

	@RequestMapping(value = "/patient", method = RequestMethod.POST)
	public ModelAndView viewPatientPost(@RequestParam int id, @RequestParam String fromDate,
			@RequestParam String toDate, HttpSession httpSession) throws Exception {

		if (null == httpSession.getAttribute("username") || "".equals(httpSession.getAttribute("username"))) {
			return new ModelAndView(new RedirectView("login"));
		}

		ModelAndView result = new ModelAndView("patient");
		result.addObject("patient", patientService.getPatient(id));
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date fromDateFormatted = df.parse(fromDate);
		Date toDateFormatted = df.parse(toDate);
		List<Measure> totalMeasures = measureService.getAllMeasuresByPatientIdBetweenDates(id, fromDateFormatted, toDateFormatted);
		List<Integer> measuresArray = new ArrayList<Integer>();
		List<String> measureDates = new ArrayList<String>();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy hh:mm");
		for (int i = 0; i < totalMeasures.size(); i++) {
			measuresArray.add(totalMeasures.get(i).getLevel());
			measureDates.add("'" + formatter.format(totalMeasures.get(i).getDateAdded()) + "'");
		}
		result.addObject("measures", measuresArray);
		result.addObject("measureDates", measureDates);
		return result;
	}

}
