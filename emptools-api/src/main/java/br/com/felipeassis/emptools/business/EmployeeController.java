package br.com.felipeassis.emptools.business;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.felipeassis.emptools.exception.ApplicationException;
import br.com.felipeassis.emptools.model.Employee;
import br.com.felipeassis.emptools.util.TreatNull;
import br.com.felipeassis.emptools.util.TreatString;

/**
 * Controller class defined to manage the Employee data.
 * @author Felipe dos Santos Assis - felipesntsassis@gmail.com
 * @package br.com.felipeassis.emptools.business
 * @since 18/04/2018
 */
@Controller
@Path("/employee/")
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
	 * Logic to list all Employee's basic data.
	 * @throws ApplicationException
	 */
	@Get("/list")
	public void listAllEmployees() throws ApplicationException {
		try {
			List<Employee> employees = employeeBusiness.listAll();
			JsonArray rows = new JsonArray();
			
			if (TreatNull.isEmpty(employees)) {
				employees.forEach(employee -> {
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
					} else {
						String[] projetos = (String[]) employee.getProjects().stream().map(p -> p.toString()).toArray();
						json.addProperty("projects", "");
					}
					
				});
			}
			
			result.use(Results.json()).withoutRoot().from(employees).serialize();
		} catch (Exception e) {
			
		}
	}

	/**
	 * Logic to list all Employee's wth complementar data.
	 * @throws ApplicationException
	 */
	@Get("/list/all")
	public void listAllDetailedEmployees() throws ApplicationException {
		try {
			List<Employee> employees = employeeBusiness.listAll();
			JsonArray rows = new JsonArray();
			
			if (TreatNull.isNotEmpty(employees)) {
				employees.forEach(employee -> {
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
						Stream<String> map = employee.getProjects().stream().map(p -> p.getCustomer());
						json.addProperty("projects", new Gson().toJson(map.toArray()));
					} else {
						json.addProperty("projects", "");
					}
					
					rows.add(json);
				});
			}
			
			result.use(Results.json()).withoutRoot().from(rows).serialize();
		} catch (Exception e) {
			JsonObject message = new JsonObject();
			message.addProperty("type", "error");
			message.addProperty("message", e.getMessage());
			result.use(Results.json()).withoutRoot().from(message).serialize();
			
		}
	}

}
