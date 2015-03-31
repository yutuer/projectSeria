package com.pureland.common.util.parseExcel;

import java.io.*;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class MyUtil {

	public static final long SECOND = 1000L;

	public static final long MINUTE = 60L * SECOND;

	public static final long HOUR = 60L * MINUTE;

	public static final long DAY = 24L * HOUR;

	public static final Set<String> TypeSet = new HashSet<String>();

	public static TreeNode getTreeNode() {
		return new DefaultMutableTreeNode();
	}

	public static void throwException(String message) {
		throw new RuntimeException();
	}

	public static void throwException() {
		throw new RuntimeException("111111111");
	}

	public static <T> List<T> getArrayList() {
		return new ArrayList<T>();
	}

	public static <T> LinkedList<T> getLinkedList() {
		return new LinkedList<T>();
	}

	public static <K, V> Map<K, V> getHashMap() {
		return new HashMap<K, V>();
	}

	public static <K, V> LinkedHashMap<K, V> getLinkedHashMap() {
		return new LinkedHashMap<K, V>();
	}

	public static <K, V> Map<K, V> getConcurrentHashMap() {
		return new ConcurrentHashMap<K, V>();
	}

	/**
	 * 得到指定时间的后一天的0点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextDay_0_ClockMillis(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的后day+1天的21点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextDay_Randomday_ClockMillis(long millis, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.add(Calendar.DAY_OF_YEAR, day);
		c.set(Calendar.HOUR_OF_DAY, 21);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis();
	}

	public static void main(String[] args) throws ParseException {
		Map<Integer, Integer> map = MyUtil.getHashMap();
		{
			for (int i = 0; i < 10; i++) {
				map.put(i, 2 * i);
			}
		}
		map.entrySet();
		deleteMapOneEntryByValue(map, 4);
		System.out.println(map);
	}

	/**
	 * 得到指定时间的后一周的指定星期几的0点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextWeek_DayOfWeek_0_ClockMillis(long millis, int dayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (c.getTimeInMillis() < millis) {
			c.add(Calendar.WEEK_OF_YEAR, 1);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的后一周的指定星期几的0点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextWeek_DayOfWeek_When_ClockMillis(long millis, int dayOfWeek, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		if (c.getTimeInMillis() < millis) {
			c.add(Calendar.WEEK_OF_YEAR, 1);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的下一个6点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextDay_6_ClockMillis(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.HOUR_OF_DAY, 6);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (c.getTimeInMillis() < millis) {
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的下一个6点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextDay_12_ClockMillis(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.HOUR_OF_DAY, 12);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (c.getTimeInMillis() < millis) {
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的下一个6点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextDay_15_ClockMillis(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.HOUR_OF_DAY, 15);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (c.getTimeInMillis() < millis) {
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的下一个6点的毫秒表示
	 *
	 * @param millis
	 *            millis
	 * @return
	 */
	public static long getNextDay_22_ClockMillis(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.HOUR_OF_DAY, 22);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (c.getTimeInMillis() < millis) {
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的下一个时分秒的毫秒表示
	 *
	 * @param millis
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static long getNextDay_Clock_ClockMillis(long millis, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		if (c.getTimeInMillis() < millis) {
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到指定时间的下一个半点时间
	 *
	 * @return
	 */
	public static long getNextHalfHourMillis(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.add(Calendar.MINUTE, 30);
		if (c.get(Calendar.MINUTE) > 30) {
			c.set(Calendar.MINUTE, 30);
		}
		return c.getTimeInMillis();
	}

	/**
	 * 得到根据str连接后的字符串
	 *
	 * @param collection
	 * @param prefixStr
	 * @param joinStr
	 * @param suffixStr
	 * @return
	 */
	public static <T> String getJoinString(Collection<T> collection, String prefixStr, String joinStr, String suffixStr) {
		// 得到应该插入的面板玩家ids字符串
		StringBuilder sb = new StringBuilder(prefixStr);
		String str = getJoinString0(collection, joinStr);
		sb.append(str);
		sb.append(suffixStr);
		return sb.toString();
	}

	private static <T> String getJoinString0(Collection<T> collection, String joinStr) {
		StringBuilder sb = new StringBuilder();
		for (T member : collection) {
			sb.append(member);
			sb.append(joinStr);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public static String getStackMessage(Exception re) {
		StringBuilder sb = new StringBuilder("\n\n\n");
		{
			StackTraceElement[] stacks = re.getStackTrace();
			sb.append(re.toString() + "\n");
			for (StackTraceElement stack : stacks) {
				sb.append("\tat " + stack + "\n");
			}
		}

		Throwable ourCause = re.getCause();
		if (ourCause != null) {
			StackTraceElement[] stacks = ourCause.getStackTrace();
			sb.append("Caused by  " + ourCause.toString() + "\n");
			for (StackTraceElement stack : stacks) {
				sb.append("\tat " + stack + "\n");
			}
		}
		return sb.toString();
	}

	public static <V> Set<V> getHashSet() {
		return new HashSet<V>();
	}

	public static <V> Set<V> getTreeSet() {
		return new TreeSet<V>();
	}

	/**
	 * 是否是同一天
	 *
	 * @param lastTime
	 * @param now
	 * @return
	 */
	public static boolean isSameDay(long lastTime, long now) {
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(lastTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(now);
		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 是否是同一周
	 *
	 * @param lastContributeTime
	 * @param now
	 * @return
	 */
	public static boolean isSameWeek(long lastContributeTime, long now) {
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(lastContributeTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(now);
		boolean isTrue = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
		return isTrue && (getChinaWeek(c1) == getChinaWeek(c2));
	}

	/**
	 * 得到中国地区的week
	 *
	 * @param c
	 * @return
	 */
	private static int getChinaWeek(Calendar c) {
		// 得到星期几
		int WeekDay = c.get(Calendar.DAY_OF_WEEK);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		int retWeek = WeekDay == 1 ? week - 1 : week;
		return retWeek;
	}

	/**
	 * 概率函数,
	 * <p/>
	 * <p/>
	 * key 是要抽取的id ,value是各个概率
	 *
	 * @param map
	 *            此map应该是linkedmap
	 * @return
	 */
	public static <T> T ratioRandom(LinkedHashMap<T, Integer> map) {
		List<Integer> list = MyUtil.getArrayList();
		int sum = 0;
		list.add(sum);
		for (Entry<T, Integer> entry : map.entrySet()) {
			sum += entry.getValue();
			list.add(sum);
		}
		int max = list.get(list.size() - 1);
		int random = (int) (Math.random() * max);
		// MyUtil.getMyLogger().error("myutil roll点,random=" + random);
		List<T> c = new ArrayList<T>(map.keySet());
		for (int i = 0; i < c.size(); i++) {
			if (list.get(i) <= random && random < list.get(i + 1)) {
				return c.get(i);
			}
		}
		return null;
	}

	/**
	 * 将第一个字符转换成小写
	 *
	 * @param str
	 * @return
	 */
	public static String firstChar2Lower(String str) {
		if (str == null || str.length() == 0) {
			throw new RuntimeException("参数长度不能为0!!");
		}
		String first = str.substring(0, 1);
		return first.toLowerCase() + str.substring(1);
	}

	/**
	 * 将第一个字符转换成大写
	 *
	 * @param str
	 * @return
	 */
	public static String firstChar2Upper(String str) {
		if (str == null || str.length() == 0) {
			throw new RuntimeException("参数长度不能为0!!");
		}
		String first = str.substring(0, 1);
		return first.toUpperCase() + str.substring(1);
	}

	/**
	 * 通过value 删除map中的entry
	 *
	 * @param map
	 * @param condition
	 * @return
	 */
	public static <K, V> Map<K, V> deleteMapOneEntryByValue(Map<K, V> map, V condition) {
		for (Iterator<Entry<K, V>> iter = map.entrySet().iterator(); iter.hasNext();) {
			Entry<K, V> entry = iter.next();
			if (entry.getValue().equals(condition)) {
				iter.remove();
			}
		}
		return map;
	}

	public static void writeFile(File f, byte[] array) {
		FileOutputStream fos = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream(f);
			fos.write(array);
		} catch (IOException e) {
			Logger.getRootLogger().info(String.format("writeFile失败:%s\n", f.getAbsolutePath()));
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static byte[] readFile(String fileName) {
		byte[] b = null;
		File f = new File(fileName);
		if (!f.exists()) {

		} else {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				b = new byte[fis.available()];
				fis.read(b);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	public static class BinaryHeap<T extends Comparable<? super T>> {
		private static final int DEFAULT_CAPACITY = 10;

		private T[] array;
		private int currentSize;

		public BinaryHeap() {
			this(DEFAULT_CAPACITY);
		}

		public BinaryHeap(int capacity) {
			array = (T[]) new Comparable[capacity];
		}

		public BinaryHeap(T[] array) {
			this.array = (T[]) new Comparable[(array.length + 2) * 11 / 10];
			for (T t : array) {
				insert(t);
			}
		}

		public void insert(T x) {
			if (currentSize == array.length - 1) {
				enlargeArray();
			}
			int hole = ++currentSize;
			// 增加hole > 1 是为了 及时终止
			for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2) {
				array[hole] = array[hole / 2];
			}
			array[hole] = x;
		}

		public T deleteMin() {
			if (currentSize == 0) {
				throw new RuntimeException("");
			}
			T minItem = findMin();
			// 首先将最后一个放入到头位置
			array[1] = array[currentSize--];

			// 释放掉最后一个
			array[currentSize + 1] = null;

			// 开始下滤
			percolateDown(1);
			return minItem;
		}

		/**
		 * 下滤
		 *
		 * @param hole
		 */
		private void percolateDown(int hole) {
			int child = 0;
			T tmp = array[hole];
			for (; hole * 2 <= currentSize; hole = child) {
				if (array[hole * 2] == null) {
					break;
				}
				if (array[hole * 2 + 1] == null) {
					child = hole * 2;
				} else {
					child = array[hole * 2].compareTo(array[hole * 2 + 1]) < 0 ? hole * 2 : hole * 2 + 1;
				}
				if (array[child].compareTo(tmp) < 0) { // 说明需要上滤
					array[hole] = array[child];
				} else {// 这里一定要break
					break;
				}
			}
			array[hole] = tmp;
		}

		public T findMin() {
			return array[1];
		}

		private void enlargeArray() {
			array = Arrays.copyOf(array, array.length * 2 + 1);
		}

		public int getCurrentSize() {
			return currentSize;
		}
	}

	public static class BinarySearchTree<T extends Comparable<? super T>> {

		private static class BinaryNode<T> {

			public BinaryNode(T element) {
				super();
				this.element = element;
			}

			public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
				super();
				this.element = element;
				this.left = left;
				this.right = right;
			}

			T element;
			BinaryNode<T> left;
			BinaryNode<T> right;
		}

		private BinaryNode<T> root;

		public BinarySearchTree(T x) {
			super();
			this.root = new BinaryNode<T>(x);
		}

		public void makeEmpty() {
			root = null;
		}

		public boolean isEmpty() {
			return root == null;
		}

		public boolean contains(T x) {
			return contains(x, root);
		}

		public boolean contains(T x, BinaryNode<T> node) {
			if (node == null) {
				return false;
			}
			int result = x.compareTo(node.element);
			if (result == 0) {
				return true;
			} else if (result < 0) {
				return contains(x, node.left);
			} else if (result > 0) {
				return contains(x, node.right);
			}
			return false;
		}

		public BinaryNode<T> findMax(BinaryNode<T> node) {
			if (node == null) {
				return null;
			}
			if (node.right == null) {
				return node;
			}
			return findMax(node.right);
		}

		public BinaryNode<T> findMin(BinaryNode<T> node) {
			if (node != null) {
				while (node.left != null) {
					node = node.left;
				}
			}
			return node;
		}

		public BinaryNode<T> insert(T x) {
			return insert(x, root);
		}

		public BinaryNode<T> insert(T x, BinaryNode<T> node) {
			if (node == null) {
				return new BinaryNode<T>(x);
			}

			int result = x.compareTo(node.element);

			if (result == 0) {

			} else if (result > 0) {
				node.right = insert(x, node.right);
			} else if (result < 0) {
				node.left = insert(x, node.left);
			}
			return node;
		}

		public void show() {
			show(root);
		}

		private void show(BinaryNode<T> node) {
			if (node != null) {
				show(node.left);
				System.out.println(node.element);
				show(node.right);
			}
		}

	}

	public static String getNowTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
		return sdf.format(new Date());
	}

	public static Logger getMyLogger() {
		if (isYutuer()) {
			return Logger.getRootLogger();
		}
		return Logger.getLogger("fight");
	}

	public static boolean isYutuer() {
		try {
			return "yutuer".equalsIgnoreCase(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}

	static {
		TypeSet.add("long");
		TypeSet.add("Long");
		TypeSet.add("int");
		TypeSet.add("Integer");
		TypeSet.add("short");
		TypeSet.add("Short");
		TypeSet.add("char");
		TypeSet.add("Char");
		TypeSet.add("byte");
		TypeSet.add("Byte");
		TypeSet.add("String");
		TypeSet.add("Boolean");
		TypeSet.add("boolean");
	}

	public static void parseObj2String(Object o, StringBuilder sb) throws Exception {
		Set set = MyUtil.getHashSet();
		sb.append(o + ":\n");
		String name = o.getClass().getName();
		if (isIndex(name)) {
			sb.append(o).append("\n");
		} else if (name.indexOf("[L") != -1) {
			Object[] os = (Object[]) o;
			sb.append("[\n");
			for (Object e : os) {
				parseObj2String(e, set, sb);
			}
			sb.append("]\n");
		} else if (name.indexOf("Map") != -1) {
			Map m = (Map) o;
			for (Object e : m.keySet()) {
				sb.append(e).append(":").append(m.get(e)).append("\n");
			}
		} else if (name.indexOf("List") != -1) {
			List l = (List) o;
			for (Object e : l) {
				sb.append(e).append("\n");
			}
		} else if (name.indexOf("Set") != -1) {
			Set l = (Set) o;
			for (Object e : l) {
				sb.append(e).append("\n");
			}
		} else if (name.indexOf("Logger") != -1) {

		} else if (name.startsWith("cn.com.game.")) {
			set.add(o);
			parseObj2String(o, set, sb);
		} else {
			set.add(o);
			sb.append(o);
		}
	}

	private static void parseObj2String(Object o, Set set, StringBuilder sb) throws Exception {
		if (o == null)
			return;
		Class c = o.getClass();
		sb.append("\t");
		if (isIndex(c.getName())) {
			sb.append(c.getName()).append(":").append(o).append("\n");
			return;
		} else {

		}
		sb.append(c.getName()).append(":").append("\n");
		for (Field f : c.getDeclaredFields()) {
			f.setAccessible(true);
			String name = f.getType().getName();
			sb.append(f.getName()).append(":");
			sb.append(f.getType().getName()).append(":");

			Object value = f.get(o);
			if (value == null)
				continue;
			if (isIndex(name)) {
				sb.append(value).append("\n");
			} else if (name.indexOf("[L") != -1) {
				Object[] os = (Object[]) value;
				sb.append("[\n");
				for (Object e : os) {
					parseObj2String(e, set, sb);
				}
				sb.append("]\n");
			} else if (name.indexOf("Entry") != -1) {
				Entry e = (Entry) value;
				sb.append("[\n");
				parseObj2String(e.getKey(), set, sb);
				sb.append(":");
				parseObj2String(e.getValue(), set, sb);
				sb.append("]\n");
			} else if (name.indexOf("Map") != -1) {
				Map m = (Map) value;
				sb.append("[\n");
				for (Object e : m.keySet()) {
					sb.append("key:");
					parseObj2String(e, set, sb);
					sb.append(" --- value:");
					parseObj2String(m.get(e), set, sb);
					sb.append("\n");
				}
				sb.append("]\n");
			} else if (name.indexOf("List") != -1) {
				List l = (List) value;
				sb.append("[\n");
				for (Object e : l) {
					parseObj2String(e, set, sb);
					sb.append("\n");
				}
				sb.append("]\n");
			} else if (name.indexOf("Set") != -1) {
				Set l = (Set) value;
				for (Object e : l) {
					parseObj2String(e, set, sb);
					sb.append("\n");
				}
			} else if (name.indexOf("Logger") != -1) {
				sb.append("\n");
			} else if (name.startsWith("cn.com.game.")) {
				if (set.contains(value)) {
					sb.append(value).append("\n");
				} else {
					set.add(value);
					parseObj2String(value, set, sb);
				}
			} else {
				if (set.contains(value)) {
					sb.append(value).append("\n");
				} else {
					set.add(value);
					sb.append(value).append("\n");
				}
			}
		}
	}

	private static boolean isIndex(String name) {
		for (String s : TypeSet) {
			if (name.indexOf(s) != -1) {
				return true;
			}
		}
		return false;
	}

	public static class MyClassLoader extends ClassLoader {
		private String filePath;

		public MyClassLoader(String filePath) {
			this.filePath = filePath;
		}

		@SuppressWarnings("rawtypes")
		protected Class<?> findClass(String className) throws ClassNotFoundException {
			Class clazz = this.findLoadedClass(className);
			if (null == clazz) {
				try {
					String classFile = getClassFile(className);
					FileInputStream fis = new FileInputStream(classFile);
					FileChannel fileC = fis.getChannel();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					WritableByteChannel outC = Channels.newChannel(baos);
					ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
					while (true) {
						int i = fileC.read(buffer);
						if (i == 0 || i == -1) {
							break;
						}
						buffer.flip();
						outC.write(buffer);
						buffer.clear();
					}
					fis.close();
					byte[] bytes = baos.toByteArray();
					clazz = defineClass(className, bytes, 0, bytes.length);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return clazz;
		}

		@SuppressWarnings("unused")
		private byte[] loadClassBytes(String className) throws ClassNotFoundException {
			try {
				String classFile = getClassFile(className);
				FileInputStream fis = new FileInputStream(classFile);
				FileChannel fileC = fis.getChannel();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				WritableByteChannel outC = Channels.newChannel(baos);
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
				while (true) {
					int i = fileC.read(buffer);
					if (i == 0 || i == -1) {
						break;
					}
					buffer.flip();
					outC.write(buffer);
					buffer.clear();
				}
				fis.close();
				return baos.toByteArray();
			} catch (IOException fnfe) {
				throw new ClassNotFoundException(className);
			}
		}

		private String getClassFile(String name) {
			StringBuffer sb = new StringBuffer(filePath);
			name = name.replace('.', File.separatorChar) + ".class";
			sb.append(File.separator + name);
			return sb.toString();
		}
	}

	public static String getFileContent(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s).append("\n");
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 解析xml,返回的对象用
	 * <p/>
	 * XPath xpath = XPathFactory.newInstance().newXPath(); Node node =
	 * (Node)xpath.evaluate("robot", document, XPathConstants.NODE);
	 * <p/>
	 * 处理
	 *
	 * @param fileName
	 * @return
	 */
	public static Document parseXml(String fileName) {
		DocumentBuilder builder = null;
		InputStream cfgFile = null;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			cfgFile = ClassLoader.getSystemResourceAsStream(fileName);
			Document document = builder.parse(cfgFile);
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				cfgFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
