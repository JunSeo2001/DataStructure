package MidPractice;

public class MidExam {

		// no.1 : Num & namd, 
		// in. num. :             name :
		
		
		// no.2 : change MyStack with MyArrayList2 for Record type data
		static class MyStack extends MyArrayList2mid {
			MyArrayList2mid<String> stack ;
			public int stackPointer() {
				return stack.size();
			}
			public MyStack() {
				stack = new MyArrayList2mid<>();
			}
			public void push(String d) {
				stack.addLast(d);
			}
		
			public String pop() {
				if (stack.isEmpty()) {
					return null;
				} else {
					String temp = stack.get(stack.size()-1);
					stack.removeLast();
					return temp;

				}

			}
			public boolean isEmpty() {
				return stack.isEmpty(); 
			}
			public String peek() {
				if (stack.isEmpty()) {
					return null;
				} else {
					String temp = stack.get(stack.size()-1);
					return temp;
				}
				
			}
			public void showStack() {
				stack.showList();
			}
		}
		
//		 no.3 : change MyQueue with MyLinkedList2 for Record type data
		static class MyQueue{
			MyLinkedList2mid<String> queue ;
			public MyQueue(){
				queue = new MyLinkedList2mid<>();
			}
			public String front() {
				return queue.get(0);
			}
			public String rear() {
				return queue.get(queue.size());
			}
			public void enqueue(String value) { // add
				queue.addLast(value);

			}
			public String dequeue() {
				if (queue.first == null) {
					return null;
				} else {
					String temp = queue.get(0);
					queue.removeFirst();
					return temp;
				}
			}
			public boolean isEmpty() {
				// fill here
				if (queue.size() == 0) {
					return true;
				}else {
					return false;
				}
			}
			public String peek() {
				// fill
				if (queue.first == null) {
					return null;
				} else {
					return queue.get(0);
				}

			}
			public int size() {
				return queue.size();
			}
			public void showQueue() {
				queue.showList();
			}
		}

		// no.4 : make a method "sortNAdd" into MyLinkeListS
		static class MyLinkedListS extends MyLinkedList1mid {

			public void sortedAdd(int value) {
				Node newNode = new Node(value);
				if (first == null || first.data >= value) {
					addFirst(value);
				} else {
					Node current = first;
					while (current.next != null && current.next.data < value) {
						// 적절한 위치를 찾을 때까지 노드를 순회
						current = current.next;
					}
					if (current.next == null) {
						// 마지막까지 갔는데 삽입할 위치를 못 찾은 경우
						addLast(value);
					} else {
						// 찾은 위치에 노드 삽입
						Node temp = current.next;
						current.next = newNode;
						newNode.next = temp;
						temp.prev = newNode;
						nOfNodes++;
					}
				}

			}

			public int indexOf(int value) {
				return indexOf(first, value, -1);
			}

			private int indexOf(Node f, int value, int index) {
				if (f == null) {
					// 노드가 더 이상 없으면 찾는 값이 없다는 의미로 -1 반환
					return -1;
				}
				index++; // 다음 노드로 넘어가므로 인덱스를 1 증가
				if (f.data == value) {
					// 현재 노드의 데이터가 찾는 값과 같으면 현재 인덱스 반환
					return index;
				} else {
					// 아니면 다음 노드를 재귀적으로 탐색
					return indexOf(f.next, value, index);
				}
			}
		}

		// no.5 : scheduler
		static class Scheduler {
			private class Element {
				int date;
				MyLinkedList2mid<String> taskList;

				private Element(int d) {
					date = d;
					taskList = new MyLinkedList2mid<>();
				}
			}

			MyArrayList2mid<Element> table;

			public Scheduler() {
				table = new MyArrayList2mid<>();
			}

			private int getIndex(int d) {
				for (int i = 0; i < table.size(); i++) {
					if (table.get(i).date == d)
						return i;
				}
				return -1;
			}

			public void addTask(int d, String t) {
				int index = getIndex(d);
				if (index == -1) { // 해당 date가 table에 없으면 새로운 Element 생성 후 추가
					Element newElement = new Element(d);
					newElement.taskList.addLast(t);
					table.addLast(newElement);
				} else { // 해당 date가 table에 있으면 기존의 taskList에 추가
					Element existingElement = table.get(index);
					existingElement.taskList.addLast(t);
				}


			}

			public void freeTask(int d, String t) {
				int index = getIndex(d);
				if (index == -1) {
					System.out.println("Error: No such date exists");
				} else {
					Element element = table.get(index);
					int taskIndex = element.taskList.indexOf(t);
					if (taskIndex != -1) {
						element.taskList.removeIndex(taskIndex);
						// taskList가 비어있으면 해당 date를 table에서 제거
						if (element.taskList.size() == 0) {
							table.removeIndex(index);
						}
					} else {
						System.out.println("Task not found on given date");
					}
				}
			}
				public void showSchedule () {
					for (int i = 0; i < table.size(); i++) {
						System.out.print("\n[" + table.get(i).date + "] ");

						table.get(i).taskList.showList();
					}
				}

			}


			public static void main(String[] args) {

				// no. 1 : id. num & name !

				// no.2 : new MyStack for Record using MyArrayList2
				System.out.println("\n[2] Stack ==================");
				MyStack s = new MyStack();
				System.out.println("\nInitial Stack Status : Empty is " + s.isEmpty());
				s.push("aaa");
				s.push("bbb");
				s.push("ccc");
				System.out.print("After 3 pushes : ");
				s.showStack();
				System.out.println("\nPeek Test : " + s.peek() + " >> The size is " + s.stackPointer());
				System.out.println("Pop Test : " + s.pop() + " >> The size is " + s.stackPointer());
				System.out.print("Stack is : ");
				s.showStack();

				// no. 3 : new MyQueue for Record using MyArrayList2
				System.out.println("\n\n[3] Queue ==================");
				MyQueue q = new MyQueue();
				System.out.println("Initial Queue Status : Empty is " + q.isEmpty());
				q.enqueue("aaa");
				q.enqueue("bbb");
				q.enqueue("ccc");
				System.out.print("After 3 enqueues : ");
				q.showQueue();
				System.out.println("\nPeek Test : " + q.peek() + " >> The size is " + q.size());
				System.out.println("Dequeue Test : " + q.dequeue() + " >> The size is " + q.size());
				System.out.print("Queue is : ");
				q.showQueue();
				System.out.println();

//			 no. 4 : Sorted List
				System.out.println("\n[4] Sorted List ==================");
				MyLinkedListS sl = new MyLinkedListS();
				sl.sortedAdd(4);
				sl.sortedAdd(1);
				sl.sortedAdd(5);
				sl.sortedAdd(3);
				sl.sortedAdd(8);
				sl.showList();
				System.out.println();
				System.out.println("Index of 3 : " + sl.indexOf(3));
				System.out.println("Index of 8 : " + sl.indexOf(8));
				System.out.println("Index of 1 : " + sl.indexOf(1));

				// no. 5 : Scheduler
				System.out.println("\n[5] Scheduler ==================\n");
				Scheduler ms = new Scheduler();

				ms.addTask(2, "Reading");
				ms.addTask(13, "Coding");
				ms.addTask(5, "Soccer");
				ms.addTask(2, "swim");
				ms.addTask(5, "writing");
				ms.addTask(2, "party");
				System.out.println("<After 6 adds> ");
				ms.showSchedule();
				ms.freeTask(2, "Reading");
				System.out.println("\n\n<After 1 free> ");
				ms.showSchedule();
				ms.freeTask(13, "Coding");
				System.out.println("\n\n<After 1 more free>");
				ms.showSchedule();
				System.out.println("\nEnd ==================");

			}
		}