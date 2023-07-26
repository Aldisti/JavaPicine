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

	public static void	callMethod(Class c, Object o) throws Exception {
		Method			method;
		String[]		tmp;
		List<Class>		params = new LinkedList<Class>();
		List<Object>	args = new LinkedList<Object>();

		System.out.println("-------------------------");
		System.out.println("Enter name of the method for call:");
		System.out.print("-> ");
		tmp = kb.nextLine().replace("(", ",").replace(")", ",").replace(" ", "").split(",");
		for (int i = 1; i < tmp.length; i++) {
			params.add(Class.forName("java.lang." + tmp[i]));
		}
		method = (tmp.length > 1) ? c.getMethod(tmp[0], params.toArray(new Class[0])) : c.getMethod(tmp[0], null);
		for (int i = 1; i < tmp.length; i++) {
			System.out.print("Enter " + tmp[i] + " value:\n-> ");
			args.add(params.get(i - 1).getConstructor(String.class).newInstance(kb.nextLine().trim()));
		}
		if (!method.getReturnType().getSimpleName().equals("void")) {
			System.out.println("Method returned:\n" + method.invoke(o, args.toArray()));
		}
		else {
			method.invoke(o, args.toArray());
			System.out.println(o.toString());
		}
	}

	public static void	main(String[] args) {
		String			line;
		Class			c = null;
		Object			o;

		System.out.println("Classes:\nUser\nProduct");
		System.out.println("-------------------------");
		System.out.print("Enter class name:\n-> ");
		line = kb.nextLine().trim();
		try {
			c = Class.forName("edu.school42.models." + line);
			showClassInfo(c);
			o = createObject(c);
			updateObject(c, o);
			callMethod(c, o);
		}
		catch (Exception e) {
			System.out.println("Error: '" + e + "'");
			return ;
		}
	}
}
