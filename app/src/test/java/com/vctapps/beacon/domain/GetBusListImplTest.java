package com.vctapps.beacon.domain;

import com.vctapps.beacon.data.bus.BusRepository;
import com.vctapps.beacon.data.busstop.BusStopRepository;
import com.vctapps.beacon.domain.entity.Bus;
import com.vctapps.beacon.domain.usecase.GetBusList;
import com.vctapps.beacon.domain.usecase.GetBusListImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Maybe;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBusListImplTest {

    @Mock
    BusRepository busRepository;

    @Mock
    BusStopRepository busStopRepository;

    String busStopIdMock = "655789";
    int busStopInIntMock = 655789;

    GetBusList getBusList;

    @Before
    public void setUp(){
        when(busStopRepository.getCloseBusStop()).thenReturn(Maybe.just(busStopIdMock));

        when(busRepository.getBusList(busStopInIntMock)).thenReturn(Maybe.just(getMockList()));

        getBusList = new GetBusListImpl(busStopRepository, busRepository);
    }

    @Test
    public void successful_get_list_bus(){
        getBusList.run()
                .test();

        verify(busStopRepository, times(1)).getCloseBusStop();
        verify(busRepository, times(1)).getBusList(busStopInIntMock);
    }

    public List<Bus> getMockList(){
        List<Bus> mockList = new ArrayList<>();

        mockList.add(getMockBus());
        mockList.add(getMockBus());
        mockList.add(getMockBus());

        return mockList;
    }

    public Bus getMockBus(){
        return new Bus(123, "B63", "Terminal A", "Terminal B", false, Calendar.getInstance());
    }

}
