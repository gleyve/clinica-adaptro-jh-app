import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ClinicaAdaptrojhAppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { PlanoEstrategicoDeleteDialogComponent } from 'app/entities/plano-estrategico/plano-estrategico-delete-dialog.component';
import { PlanoEstrategicoService } from 'app/entities/plano-estrategico/plano-estrategico.service';

describe('Component Tests', () => {
  describe('PlanoEstrategico Management Delete Component', () => {
    let comp: PlanoEstrategicoDeleteDialogComponent;
    let fixture: ComponentFixture<PlanoEstrategicoDeleteDialogComponent>;
    let service: PlanoEstrategicoService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ClinicaAdaptrojhAppTestModule],
        declarations: [PlanoEstrategicoDeleteDialogComponent],
      })
        .overrideTemplate(PlanoEstrategicoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PlanoEstrategicoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PlanoEstrategicoService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
