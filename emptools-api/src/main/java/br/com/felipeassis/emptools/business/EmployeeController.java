package br.com.felipeassis.emptools.business;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;
import br.com.felipeassis.emptools.exception.ApplicationException;
import br.com.felipeassis.emptools.model.Employee;
import br.com.felipeassis.emptools.util.ApplicationMessage;
import br.com.felipeassis.emptools.util.TreatNull;
import br.com.felipeassis.emptools.util.TreatString;

/**
 * Controller class defined to manage the Employee data.
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.business
 * @since 18/04/2018
 */
@Controller
@Path("/employee")
public class EmployeeController {

	private Result result;
	private EmployeeBusiness employeeBusiness;

	/**
	 * Default constuctor.
	 * @param result: Result
	 * @param employeeBusiness: EmployeeBusiness
	 */
	@Inject
	public EmployeeController(Result result, EmployeeBusiness employeeBusiness) {
		this.result = result;
		this.employeeBusiness = employeeBusiness;
	}

	@Deprecated
	public EmployeeController() {}

	/**
	 * Logic to list all Employees with basic data.
	 * @throws ApplicationException
	 */
	@Get("/list")
	public void list() throws ApplicationException {
		try {
			List<Employee> employees = employeeBusiness.listAllWithDetails();
			JsonArray rows = new JsonArray();
			
			if (TreatNull.isNotEmpty(employees)) {
				employees.forEach(employee -> {
					rows.add(prepareJsonData(employee));
				});
			}
			
			result.use(Results.json()).withoutRoot().from(rows).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from(ApplicationMessage.getJsonMessage(ApplicationMessage.MSG_TYPE_ERROR, e.getMessage())).serialize();
		}
	}

	/**
	 * Logic to list the Employeees by filter.
	 * @param filter
	 */
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Post("/list")
	public void list(EmployeeFilter filter) {
		try {
			employeeBusiness.findByFilter(filter);
			JsonArray rows = new JsonArray();
			
			if (TreatNull.isNotEmpty(filter.getRows())) {
				filter.getRows().forEach(employee -> {
					rows.add(prepareJsonData(employee));
				});
			}
			
			result.use(Results.json()).withoutRoot().from(rows).serialize();
		} catch (ApplicationException e) {
			result.use(Results.json()).withoutRoot().from(ApplicationMessage.getJsonMessage(
					ApplicationMessage.MSG_TYPE_WARNING, e.getMessage())).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from(ApplicationMessage.getJsonMessage(
					ApplicationMessage.MSG_TYPE_ERROR, e.getMessage())).serialize();
		}
	}

	/**
	 * Logic to prepare rows converting it in JSON format.
	 * @param employee: Employee
	 * @return JsonObject
	 */
	private JsonObject prepareJsonData(Employee employee) {
		JsonObject json = new JsonObject();
		json.addProperty("name", employee.getName());
		json.addProperty("role", employee.getRole().getDescription());
		json.addProperty("salary", TreatString.formatarMoeda(employee.getSalary().doubleValue()));
		
		if (TreatNull.isNotNull(employee.getManager())) {
			json.addProperty("manager", employee.getManager().getName());
		} else {
			json.addProperty("manager", "");
		}
		if (TreatNull.isNotEmpty(employee.getProjects())) {
			Stream<String> projectStream = employee.getProjects().stream().map(p -> p.getCustomer());
			json.addProperty("projects", new Gson().toJson(projectStream.toArray()));
		} else {
			json.addProperty("projects", "");
		}
		if (TreatNull.isNotEmpty(employee.getSkills()) ) {
			Stream<String> skillStream = employee.getSkills().stream().map(s -> s.getDescription());
			json.addProperty("skills", new Gson().toJson(skillStream.toArray()));
		} else {
			json.addProperty("skills", "");
		}
		if (TreatNull.isNotEmpty(employee.getCertifications())) {
			Stream<String> certificationStream = employee.getCertifications().stream().map(
					c -> c.getDescription());
			json.addProperty("certification", new Gson().toJson(certificationStream.toArray()));
		} else {
			json.addProperty("certification", "");
		}
		
		return json;
	}

}
