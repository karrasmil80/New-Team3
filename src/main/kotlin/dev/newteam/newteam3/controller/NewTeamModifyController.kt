package dev.newteam.newteam3.controller

import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import javafx.fxml.FXML
import javafx.scene.control.*

class NewTeamModifyController {

    @FXML
    lateinit var buttonGuardar: Button

    @FXML
    lateinit var buttonCancelar: Button

    @FXML
    lateinit var paisField: TextField

    @FXML
    lateinit var nombreField: TextField

    @FXML
    lateinit var rolComboBox: ComboBox<Any>

    @FXML
    lateinit var fechaIncorporacionField: DatePicker

    @FXML
    lateinit var numPartidosLabel: Label

    @FXML
    lateinit var numGolesLabel: Label

    @FXML
    lateinit var partidosField: TextField

    @FXML
    lateinit var golesField: TextField

    @FXML
    lateinit var pesoField: TextField

    @FXML
    lateinit var alturaField: TextField

    @FXML
    lateinit var dorsalField: TextField

    @FXML
    lateinit var salarioField: TextField

    @FXML
    lateinit var fechaNacimientoField: DatePicker

    @FXML
    lateinit var apellidosField: TextField

    var persona: Persona? = null

    var mainController: NewTeamController? = null

    @JvmName("sert persona controller")
    fun setPersona(persona: Persona?) {
        this.persona = persona
        if (persona == null) {
            limpiarCamposAnyadir()
        } else {
            cargarDatos()
        }
    }

    fun initialize() {
        initDefaultValues()
        initEvents()
    }

    fun initEvents() {
        buttonCancelar.setOnAction { onCancelarButtonClick() }
        buttonGuardar.setOnAction { onGuardarJugadorButtonClick() }
    }

    fun initDefaultValues() {
        paisField.isDisable = false
        nombreField.isDisable = true
        apellidosField.isDisable = false
        apellidosField.isEditable = true
        rolComboBox.isDisable = false
        rolComboBox.isEditable = true
        fechaIncorporacionField.isDisable = false
        fechaIncorporacionField.isEditable = true
        alturaField.isDisable = false
        alturaField.isEditable = true
        pesoField.isEditable = true
        pesoField.isDisable = false
        salarioField.isDisable = false
        salarioField.isEditable = true
        dorsalField.isDisable = false
        dorsalField.isEditable = true
        fechaNacimientoField.isDisable = false
        fechaNacimientoField.isEditable = true
        golesField.isDisable = false
        golesField.isEditable = true
        partidosField.isDisable = false
        partidosField.isEditable = true
        nombreField.isDisable = false
        nombreField.isEditable = true
        buttonGuardar.isDisable = false


    }


    fun limpiarCamposAnyadir() {
        nombreField.text = ""
        apellidosField.text = ""
        paisField.text = ""
        dorsalField.text = ""
        salarioField.text = ""
        alturaField.text = ""
        pesoField.text = ""
        golesField.text = ""
        partidosField.text = ""
        fechaNacimientoField.value = null
        fechaIncorporacionField.value = null
        numGolesLabel.text = "0"
        numPartidosLabel.text = "0"
        rolComboBox.selectionModel.clearSelection()
    }


    private fun cargarDatos() {
        persona?.let { persona ->
            nombreField.text = persona.nombre
            apellidosField.text = persona.apellido
            dorsalField.text = persona.fechaIncorporacion
            paisField.text = persona.pais

            if (persona is Jugador) {
                val jugador = persona
                dorsalField.text = jugador.dorsal.toString()
                salarioField.text = jugador.salario.toString()
                alturaField.text = jugador.altura.toString()
                pesoField.text = jugador.peso.toString()
                golesField.text = jugador.goles.toString()
                partidosField.text = jugador.partidosJugados.toString()
                numGolesLabel.text = jugador.goles.toString()
                numPartidosLabel.text = jugador.partidosJugados.toString()
            }
        }
    }

    fun onCancelarButtonClick() {
        buttonCancelar.setOnAction {
            Alert(Alert.AlertType.WARNING).apply {
                title = "CANCELAR"
                headerText = "Seguro que quieres dejar de editar al miembro"
                contentText = "Se perderan los datos no guardados"
            }
        }
    }

    fun onGuardarJugadorButtonClick() {

        persona?.let { persona ->
            persona.nombre = nombreField.text
            persona.apellido = apellidosField.text
            persona.pais = paisField.text

            if (persona is Jugador) {
                val jugador = persona
                jugador.dorsal = dorsalField.text.toIntOrNull() ?: 0
                jugador.fechaNacimiento = fechaNacimientoField.value?.toString() ?: ""
                jugador.fechaIncorporacion = fechaIncorporacionField.value?.toString() ?: ""
                jugador.salario = salarioField.text.toDoubleOrNull() ?: 0.0
                jugador.altura = alturaField.text.toDoubleOrNull() ?: 0.0
                jugador.peso = pesoField.text.toDoubleOrNull() ?: 0.0
                jugador.goles = golesField.text.toIntOrNull() ?: 0
                jugador.partidosJugados = partidosField.text.toIntOrNull() ?: 0

            }

            mainController?.tableViewNewTeam?.refresh()
            mainController?.tableViewNewTeamEntrenadores?.refresh()
            buttonGuardar.scene.window.hide()

        }
    }
}



