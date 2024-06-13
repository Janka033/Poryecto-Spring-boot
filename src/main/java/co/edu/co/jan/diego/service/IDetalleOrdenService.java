package co.edu.co.jan.diego.service;

import co.edu.co.jan.diego.dtos.OrderDetailsDto;
import co.edu.co.jan.diego.model.OrderDetails;

public interface IDetalleOrdenService {
	OrderDetailsDto save (OrderDetails orderDetails);

}
