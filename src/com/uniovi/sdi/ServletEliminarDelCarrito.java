package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/eliminarDelCarrito")
public class ServletEliminarDelCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEliminarDelCarrito() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String producto = request.getParameter("productoNombre");
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");

		if (carrito != null) {
			String producto2 = request.getParameter("producto");
			if (producto != null) {
				eliminarDelCarrito(carrito, producto);
			}
			request.setAttribute("paresCarrito", carrito);
			getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
		}

	}


	private String carritoEnHTML(HashMap<String, Integer> carrito) {
		String carritoEnHTML = "";

		for (String key : carrito.keySet()) {
			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
		}

		return carritoEnHTML;
	}

	
	
	private void eliminarDelCarrito(HashMap<String, Integer> carrito, String producto) {

		if (carrito.get(producto) != null) {
			carrito.remove(producto);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
