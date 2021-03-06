import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ClinicaAdaptrojhAppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { EspecialidadeSaudeDeleteDialogComponent } from 'app/entities/especialidade-saude/especialidade-saude-delete-dialog.component';
import { EspecialidadeSaudeService } from 'app/entities/especialidade-saude/especialidade-saude.service';

describe('Component Tests', () => {
  describe('EspecialidadeSaude Management Delete Component', () => {
    let comp: EspecialidadeSaudeDeleteDialogComponent;
    let fixture: ComponentFixture<EspecialidadeSaudeDeleteDialogComponent>;
    let service: EspecialidadeSaudeService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ClinicaAdaptrojhAppTestModule],
        declarations: [EspecialidadeSaudeDeleteDialogComponent],
      })
        .overrideTemplate(EspecialidadeSaudeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EspecialidadeSaudeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EspecialidadeSaudeService);
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
