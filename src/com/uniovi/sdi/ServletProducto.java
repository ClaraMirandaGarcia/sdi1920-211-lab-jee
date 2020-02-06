package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Servlet implementation class ServletProducto
 */
@WebServlet("/productos")
public class ServletProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProducto() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// obtener todos los productos de la base de
		//		datos, almacenándolos en una variable “productosTienda” y, finalmente,
		//		mostrando una vista “vista-productos.jsp”

		List<Producto> productosTienda = new LinkedList<Producto>();
		ObjectContainer db = null;
		try {
			db = Db4oEmbedded.openFile("C:\\Users\\CMG\\Desktop\\sts-bundle\\sts-3.9.7.RELEASE\\bdProductos");
			List<Producto> respuesta = db.queryByExample(Producto.class);
			productosTienda.addAll(respuesta);
			request.setAttribute("productosTienda", productosTienda);
			getServletContext().getRequestDispatcher("/vista-productos.jsp").forward(request, response);

		} finally {
			db.close();
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
