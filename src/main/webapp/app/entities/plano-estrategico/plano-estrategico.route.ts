import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPlanoEstrategico, PlanoEstrategico } from 'app/shared/model/plano-estrategico.model';
import { PlanoEstrategicoService } from './plano-estrategico.service';
import { PlanoEstrategicoComponent } from './plano-estrategico.component';
import { PlanoEstrategicoDetailComponent } from './plano-estrategico-detail.component';
import { PlanoEstrategicoUpdateComponent } from './plano-estrategico-update.component';

@Injectable({ providedIn: 'root' })
export class PlanoEstrategicoResolve implements Resolve<IPlanoEstrategico> {
  constructor(private service: PlanoEstrategicoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPlanoEstrategico> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((planoEstrategico: HttpResponse<PlanoEstrategico>) => {
          if (planoEstrategico.body) {
            return of(planoEstrategico.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PlanoEstrategico());
  }
}

export const planoEstrategicoRoute: Routes = [
  {
    path: '',
    component: PlanoEstrategicoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.planoEstrategico.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PlanoEstrategicoDetailComponent,
    resolve: {
      planoEstrategico: PlanoEstrategicoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.planoEstrategico.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PlanoEstrategicoUpdateComponent,
    resolve: {
      planoEstrategico: PlanoEstrategicoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.planoEstrategico.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PlanoEstrategicoUpdateComponent,
    resolve: {
      planoEstrategico: PlanoEstrategicoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'clinicaAdaptrojhApp.planoEstrategico.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
