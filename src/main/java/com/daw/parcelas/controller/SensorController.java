package com.daw.parcelas.controller;

import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;
import com.daw.parcelas.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/sensores")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @ModelAttribute("tipos")
    public TipoSensor[] tipos() {
        return TipoSensor.values();
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) TipoSensor tipo,
            Model model) {

        model.addAttribute("sensores", sensorService.buscar(q, tipo));
        model.addAttribute("q", q);
        model.addAttribute("tipoSel", tipo);

        return "sensores/lista";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("sensor", sensorService.buscarPorId(id));
        return "sensores/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("sensor", new Sensor());
        return "sensores/formulario";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("sensor", sensorService.buscarPorId(id));
        return "sensores/formulario";
    }

    @PostMapping
    public String crear(
            @Valid @ModelAttribute("sensor") Sensor sensor,
            BindingResult errores,
            RedirectAttributes flash) {

        if (errores.hasErrors()) {
            return "sensores/formulario";
        }

        sensor.setId(null);
        Sensor guardado = sensorService.guardar(sensor);

        flash.addFlashAttribute(
                "mensaje",
                "Sensor «" + guardado.getName() + "» creado correctamente");

        return "redirect:/sensores";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute("sensor") Sensor sensor,
            BindingResult errores,
            RedirectAttributes flash) {

        sensorService.buscarPorId(id); // Verificar que el sensor existe antes de actualizar
        sensor.setId(id);

        if (errores.hasErrors()) {
            return "sensores/formulario";
        }

        Sensor guardado = sensorService.guardar(sensor);

        flash.addFlashAttribute(
                "mensaje",
                "Sensor «" + guardado.getName() + "» actualizado correctamente");

        return "redirect:/sensores";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(
            @PathVariable Long id,
            RedirectAttributes flash) {

        Sensor sensor = sensorService.buscarPorId(id);
        sensorService.eliminar(id);

        flash.addFlashAttribute(
                "mensaje",
                "Sensor «" + sensor.getName() + "» eliminado correctamente");

        return "redirect:/sensores";
    }

}
