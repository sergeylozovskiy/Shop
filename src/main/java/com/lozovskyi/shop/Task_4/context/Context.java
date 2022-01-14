package com.lozovskyi.shop.Task_4.context;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_4.Controller.Controller;
import com.lozovskyi.shop.Task_4.command.Command;
import com.lozovskyi.shop.Task_4.command.impl.*;
import com.lozovskyi.shop.Task_4.command.impl.*;
import com.lozovskyi.shop.Task_4.dao.CartDAO;
import com.lozovskyi.shop.Task_4.dao.OrderDAO;
import com.lozovskyi.shop.Task_4.dao.ProductDAO;
import com.lozovskyi.shop.Task_4.dao.impl.CartDAOImpl;
import com.lozovskyi.shop.Task_4.dao.impl.OrderDAOImpl;
import com.lozovskyi.shop.Task_4.dao.impl.ProductDAOImpl;
import com.lozovskyi.shop.Task_4.input.InputHelper;
import com.lozovskyi.shop.Task_4.input.InputHelperResolver;
import com.lozovskyi.shop.Task_4.service.impl.CartServiceImpl;
import com.lozovskyi.shop.Task_4.service.impl.OrderServiceImpl;
import com.lozovskyi.shop.Task_4.service.impl.ProductServiceImpl;
import com.lozovskyi.shop.Task_4.utility.BackupUtil;
import com.lozovskyi.shop.Task_4.utility.InputUtil;

import java.util.HashMap;
import java.util.Map;

public class Context {
	public static final String BACK_UP_FILE = "inStockBackup.bin";
	public static final String BACK_UP_FILE_SUPPORTING = "inStockBackupSupporting.bin";
	private static Context instance = new Context();
	private ProductDAO productDAO = new ProductDAOImpl();
	private CartDAO cartDAO = new CartDAOImpl();
	private OrderDAO orderDAO = new OrderDAOImpl();
	private HashMap<Integer, Command> commands = new HashMap<>();
	private Controller controller = new Controller();
	private InputHelper inputHelper;

	private Context() {
	}

	public synchronized static Context getInstance() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}

	public void run() {
		initInStock();
		initCommands();
		initInputHelper();
		controller.setCommands(commands);
		controller.setContext(instance);
		int command = 0;
		while (command >= 0) {
			controller.start(command);
			command = InputUtil.getInteger();
		}
	}

	public OrderServiceImpl getOrderService() {
		return new OrderServiceImpl(cartDAO, productDAO, orderDAO);
	}

	public CartServiceImpl getCartService() {
		return new CartServiceImpl(cartDAO, productDAO);
	}

	public ProductServiceImpl getProductService() {
		return new ProductServiceImpl(productDAO);
	}

	public HashMap<Integer, Command> getCommands() {
		return commands;
	}

	public InputHelper getInputHelper() {
		return inputHelper;
	}

	private void initInStock(){
		Map<Integer, Product> inStock;
		inStock = BackupUtil.load(BACK_UP_FILE);
		productDAO.setProducts(inStock);
	}

	private void initCommands() {
		int n = 0;
		commands.put(n++, new IndexCommand());
		commands.put(n++, new AddProductInStockCatalogueCommand());
		commands.put(n++, new ViewProductsInStockCommand());
		commands.put(n++, new AddToCartCommand());
		commands.put(n++, new ViewCartCommand());
		commands.put(n++, new RemoveFromTheCartCommand());
		commands.put(n++, new ViewLastFiveAddedProductsCommand());
		commands.put(n++, new ClearCartCommand());
		commands.put(n++, new ViewNearestOrderToDateCommand());
		commands.put(n++, new CompleteOrderCommand());
		commands.put(++n, new ExitCommand());
	}

	private void initInputHelper() {
		inputHelper = new InputHelperResolver().chooseInputHelper();
	}
}