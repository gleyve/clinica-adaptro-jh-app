import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ClinicaAdaptrojhAppTestModule } from '../../../test.module';
import { PlanoEstrategicoUpdateComponent } from 'app/entities/plano-estrategico/plano-estrategico-update.component';
import { PlanoEstrategicoService } from 'app/entities/plano-estrategico/plano-estrategico.service';
import { PlanoEstrategico } from 'app/shared/model/plano-estrategico.model';

describe('Component Tests', () => {
  describe('PlanoEstrategico Management Update Component', () => {
    let comp: PlanoEstrategicoUpdateComponent;
    let fixture: ComponentFixture<PlanoEstrategicoUpdateComponent>;
    let service: PlanoEstrategicoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ClinicaAdaptrojhAppTestModule],
        declarations: [PlanoEstrategicoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PlanoEstrategicoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PlanoEstrategicoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PlanoEstrategicoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PlanoEstrategico(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PlanoEstrategico();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
