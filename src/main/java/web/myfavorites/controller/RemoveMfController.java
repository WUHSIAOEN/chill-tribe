package web.myfavorites.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.util.Core;
import web.myfavorites.service.MyFavoritesService;

@RestController
@RequestMapping("myfavorites")
public class RemoveMfController {
	
	@Autowired
	private MyFavoritesService service;

	@DeleteMapping("{id}")
	public boolean remove(@PathVariable Integer id) {
		final Core core = new Core();
		
		if (id == null) {
			core.setSuccessful(false);
		} else {
			core.setSuccessful(true);
			return service.remove(id);
		}
		return true;
	}

}
