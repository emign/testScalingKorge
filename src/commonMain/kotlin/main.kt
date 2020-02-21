import com.soywiz.klock.seconds
import com.soywiz.korge.*
import com.soywiz.korge.input.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korinject.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.Easing
import kotlin.reflect.*


suspend fun main() = Korge(width = 200, height = 200, bgcolor = Colors["#2b2b2b"]) {
	text("MyScene1: HELLO WORLD") { filtering = false }
		solidRect(100, 100, Colors.RED) {
			position(200, 200)
		}
}


//suspend fun main() = Korge(Korge.Config(module = MyModule))

object MyModule : Module() {

	override val windowSize: SizeInt
		get() = SizeInt(200,200)

	override val mainScene: KClass<out Scene> = MyScene1::class

	override suspend fun init(injector: AsyncInjector): Unit = injector.run {
		mapInstance(MyDependency("HELLO WORLD"))
		mapPrototype { MyScene1(get()) }

	}
}

class MyDependency(val value: String)

class MyScene1(val myDependency: MyDependency) : Scene() {
	override suspend fun Container.sceneInit() {
		text("MyScene1: ${myDependency.value}") { filtering = false }
		solidRect(100, 100, Colors.RED) {
			position(200, 200)
		}
	}
}