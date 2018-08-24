package Polymorphism;

public class Child extends Child2 {
	
	public void Play(){
	System.out.println("Child Play");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Parent obj = new Child();
	obj.Play();
	obj.Study();
	obj.Work();
	}

}
