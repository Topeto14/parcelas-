package com.daw.parcelas.controller;


import org.springframework.ui.Model;
import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import com.daw.parcelas.service.ParcelaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parcelas")
public class ParcelaController {

    private final ParcelaService parcelaService;

    public ParcelaController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("parcelas", parcelaService.listarTodas());
        return "parcelas/lista";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model){
        model.addAttribute("parcela", parcelaService.buscarPorId(id));
        return "parcelas/detalle";
    }

    @GetMapping("/nueva")
    public String nueva(Model model){
        model.addAttribute("parcela", new Parcela());
        model.addAttribute("tipos", TipoCultivo.values());
        return "parcelas/formulario";
    }

    @PostMapping
    public String guardar(@ModelAttribute Parcela parcela){
        parcelaService.guardar(parcela);
        return "redirect:/parcelas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id){
        parcelaService.eliminar(id);
        return "redirect:/parcelas";
    }

}
