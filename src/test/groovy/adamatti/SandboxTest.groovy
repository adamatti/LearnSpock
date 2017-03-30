package adamatti

import spock.lang.Specification

class SandboxTest extends Specification{
	def "test max"(){
		expect:
			Math.max(1,4) == 4
	}

	def "test state"(){
		given:
			List list = []
		when:
			list.add("abc")
		then:
			list.size() == old(list.size()) + 1
	}



	//Stubs can simulate behaviour
	//Stubs cannot have interactions
	def "test service stub"(){
		given:
			SampleService service = Stub()
		when:
			def result = service.sum(1,2)
		then:
			result == 0
			//0 * service.subCall()

			//result == 99
			//service.sum (_, _) >> 99
	}

	//Mocks can simulate behaviour and have interactions
	def "test service mock"(){
		given:
			SampleService service = Mock()
		when:
			def result = service.sum(1,2)
		then:
			result == 0
			0 * service.subCall()

			//service.sum (_, _) >> 99
			//result == 99
	}

	//Spies can modify behaviour and have interactions
	def "test service spy"(){
		given:
			SampleService service = Spy()
		when:
			def result = service.sum(1,2)
		then:
			1 * service.subCall()
			result == 3
			//service.sum (_ , _ ) >> 99
	}

	//Real objects cannot have interactions
	def "test service"(){
		given:
			SampleService service = new SampleService()
		when:
			def result = service.sum(1,2)
		then:
			result == 3
			0 * service.subCall()
			service.sum(_ , _) >> 99
	}
}

class SampleService{
	SampleService(){
		println "Sample service created"
	}

	int sum(int x, int y){
		println "Sum called"
		subCall()
		x + y
	}

	void subCall(){
		println "subcall called"
	}
}

//Short Version:
//A Stub() is a Stub.
//A Mock() is a Stub and Mock.
//A Spy() is a Stub, Mock and Spy.
