import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ClinicaAdaptrojhAppTestModule } from '../../../test.module';
import { EspecialidadeSaudeUpdateComponent } from 'app/entities/especialidade-saude/especialidade-saude-update.component';
import { EspecialidadeSaudeService } from 'app/entities/especialidade-saude/especialidade-saude.service';
import { EspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';

describe('Component Tests', () => {
  describe('EspecialidadeSaude Management Update Component', () => {
    let comp: EspecialidadeSaudeUpdateComponent;
    let fixture: ComponentFixture<EspecialidadeSaudeUpdateComponent>;
    let service: EspecialidadeSaudeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ClinicaAdaptrojhAppTestModule],
        declarations: [EspecialidadeSaudeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EspecialidadeSaudeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EspecialidadeSaudeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EspecialidadeSaudeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EspecialidadeSaude(123);
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
        const entity = new EspecialidadeSaude();
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
