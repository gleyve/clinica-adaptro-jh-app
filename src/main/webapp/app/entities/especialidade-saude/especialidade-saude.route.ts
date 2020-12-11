import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEspecialidadeSaude, EspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';
import { EspecialidadeSaudeService } from './especialidade-saude.service';
import { EspecialidadeSaudeComponent } from './especialidade-saude.component';
import { EspecialidadeSaudeDetailComponent } from './especialidade-saude-detail.component';
import { EspecialidadeSaudeUpdateComponent } from './especialidade-saude-update.component';

@Injectable({ providedIn: 'root' })
export class EspecialidadeSaudeResolve implements Resolve<IEspecialidadeSaude> {
  constructor(private service: EspecialidadeSaudeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEspecialidadeSaude> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((especialidadeSaude: HttpResponse<EspecialidadeSaude>) => {
          if (especialidadeSaude.body) {
            return of(especialidadeSaude.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EspecialidadeSaude());
  }
}

export const especialidadeSaudeRoute: Routes = [
  {
    path: '',
    component: EspecialidadeSaudeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.especialidadeSaude.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EspecialidadeSaudeDetailComponent,
    resolve: {
      especialidadeSaude: EspecialidadeSaudeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.especialidadeSaude.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EspecialidadeSaudeUpdateComponent,
    resolve: {
      especialidadeSaude: EspecialidadeSaudeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.especialidadeSaude.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EspecialidadeSaudeUpdateComponent,
    resolve: {
      especialidadeSaude: EspecialidadeSaudeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.especialidadeSaude.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
