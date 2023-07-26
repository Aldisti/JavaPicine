package edu.school42.reflection;

import edu.school42.models.*;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class App {

	private static Scanner	kb = new Scanner(System.in);

	public static void	showClassInfo(Class c) throws Exception {
		System.out.println("-------------------------");
		System.out.println("fields:");
		Field[]	fields = c.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("\t" + field.getType().getSimpleName() + "\t" + field.getName());
		}
		System.out.println("methods:");
		Method[]	methods = c.getDeclaredMethods();
		for (Method method : methods) {
			System.out.print("\t" + method.getReturnType().getSimpleName()
				+ "\t" + method.getName() + "(");
			Parameter[]	params = method.getParameters();
			for (int i = 0; i < params.length; i++) {
				System.out.print(params[i].getType().getSimpleName());
				if (i < params.length - 1) {
					System.out.print(", ");
				}
			}
			System.out.println(")");
		}
	}

	public static Object	createObject(Class c) throws Exception {
		Constructor		con = null;
		String			line;
		List<Object>	args;
		Object			out;

		System.out.println("-------------------------");
		for (Constructor constr : c.getDeclaredConstructors()) {
			if (constr.getParameterCount() != 0) {
				con = constr;
				break ;
			}
		}
		System.out.println("Let's create an object.");
		args = new LinkedList<Object>();
		for (Parameter param : con.getParameters()) {
			System.out.println(param.getName() + ":");
			System.out.print("-> ");
			line = kb.nextLine();
			args.add(param.getType().getConstructor(String.class).newInstance(line));
		}
		out = con.newInstance(args.toArray());
		System.out.println("Object created: " + out);
		return (out);
	}

	public static void	updateObject(Class c, Object o) throws Exception {
		Field	field;

		System.out.println("-------------------------");
		System.out.println("Enter name of the field for changing:");
		System.out.print("-> ");
		field = c.getDeclaredField(kb.nextLine());
		field.setAccessible(true);
		System.out.println("Enter " + field.getType().getSimpleName() + " value:");
		System.out.print("-> ");
		field.set(o, field.getType().getConstructor(String.class).newInstance(kb.nextLine()));
		System.out.println("Object updated: " + o);
	}

	public static void	main(String[] args) {
		String			line = "User";
		Class			c = null;
		Constructor[]	constructors;
		Object			o;

		try {
			c = Class.forName("edu.school42.models.User");
			showClassInfo(c);
			o = createObject(c);
			updateObject(c, o);

			System.out.println();
			System.out.println();

			constructors = c.getDeclaredConstructors();
			for(Constructor constructor : constructors) {
				System.out.println("Name of Constructor : "+constructor);

				System.out.println("Count of constructor parameter : "+constructor.getParameterCount());

				Parameter[] parameters = constructor.getParameters();
				for(int i = 0; i < parameters.length; i++) {
					System.out.println("Constructor's parameter : " + parameters[i]);
				}
				System.out.println();
			}
			System.out.println();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return ;
		}
	}
}
