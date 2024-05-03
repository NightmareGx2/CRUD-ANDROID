package fernando.navarro.crudfernandonavarro

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //1- Mandar a llamar a todos los elementos de la vista
        val  txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtPrecio = findViewById<EditText>(R.id.txtPrecio)
        val  txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        val  btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val rcvDatos = findViewById<RecyclerView>(R.id.rcvDatos)

        //Ponerle un layout a mi RecycleView
        rcvDatos.layoutManager = LinearLayoutManager(this)

        //2 Crear un adaptador
        val miAdaptador = Adaptador(ListaDeDatos)


        //2- Programar el boton de agregar
        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){
                //Guardar datos
                //Crear un objeto en la clase de conexion
                val  objConexion = ClaseConexion().cadenaConexion()

                //2- Crear una variable que sea igual a un Preparestatement
                val addProduct = objConexion?.prepareStatement("insert into tbProducts values(?, ?, ?)")!!
                addProduct.setString(1,txtNombre.text.toString())
                addProduct.setInt(2, txtPrecio.text.toString().toInt())
                addProduct.setInt(3, txtCantidad.text.toString().toInt())
                   addProduct.executeUpdate()
            }
        }

        //Mostrar datos


    }
}

class Adaptador(private val Datos: Array<String>){

}