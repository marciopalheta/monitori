package br.fucapi.fapeam.monitori.navdrawer;


import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.PutExtras;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public abstract class AbstractNavDrawerActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private ListView mDrawerList;
	
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
	
    private NavDrawerActivityConfiguration navConf ;
	
    private Usuario usuarioLogado;
    
	protected abstract NavDrawerActivityConfiguration getNavDrawerConfiguration();
	
	protected abstract void onNavItemSelected( int id );
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		usuarioLogado = (Usuario) getIntent().getSerializableExtra(
				PutExtras.USUARIO_LOGADO);		
		
		navConf = getNavDrawerConfiguration();
		
        setContentView(navConf.getMainLayout()); 
		
        mTitle = mDrawerTitle = getTitle();
        
        mDrawerLayout = (DrawerLayout) findViewById(navConf.getDrawerLayoutId());
        mDrawerList = (ListView) findViewById(navConf.getLeftDrawerId());
        mDrawerList.setAdapter(navConf.getBaseAdapter());
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
		this.initDrawerShadow();
		
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                getDrawerIcon(),
                navConf.getDrawerOpenDesc(),
                navConf.getDrawerCloseDesc()
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
	}
	
	protected void initDrawerShadow() {
        mDrawerLayout.setDrawerShadow(navConf.getDrawerShadow(), GravityCompat.START);
	}
	
	protected int getDrawerIcon() {
		return R.drawable.ic_drawer;
	}
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if ( navConf.getActionMenuItemsToHideWhenDrawerOpen() != null ) {
            boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        for( int iItem : navConf.getActionMenuItemsToHideWhenDrawerOpen()) {
	        	menu.findItem(iItem).setVisible(!drawerOpen);
	        }
        }
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else {
            return false;
        }
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ( keyCode == KeyEvent.KEYCODE_MENU ) {
	    	if ( this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
	    		this.mDrawerLayout.closeDrawer(this.mDrawerList);
	    	}
	    	else {
	    		this.mDrawerLayout.openDrawer(this.mDrawerList);
	    	}
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
    
    protected DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	protected ActionBarDrawerToggle getDrawerToggle() {
		return mDrawerToggle;
	}
   
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    public void selectItem(int position) {
    	NavDrawerItem selectedItem = navConf.getNavItems().get(position);
    	
    	this.onNavItemSelected(selectedItem.getId());
        mDrawerList.setItemChecked(position, true);
        
        if ( selectedItem.updateActionBarTitle()) {
        	setTitle(selectedItem.getLabel());
        }
        
        if ( this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
        	mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
    
    public void executeItem(int idItem) {
    	int position = -1;
    	NavDrawerItem selectedItem = null;    
    	List<NavDrawerItem> list = navConf.getNavItems();
    	
        for(int j = 0; j < list.size(); j++) {

            if(list.get(j).getId() == idItem) {
            	selectedItem = list.get(j);
            	position = j;
                  break;
              }
         }

    	if(selectedItem==null){
    		return;
    	}
    	//NavDrawerItem selectedItem = navConf.getNavItems().get(position);
    	navConf.getNavItems().get(position);   	
    	this.onNavItemSelected(idItem);
        mDrawerList.setItemChecked(position, true);
        
        if ( selectedItem.updateActionBarTitle()) {
        	setTitle(selectedItem.getLabel());
        }
        
        if ( this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
        	mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
    
    public void setNoItemChecked(){
    	
    	List<NavDrawerItem> list = navConf.getNavItems();
    	
        for(int j = 0; j < list.size(); j++) {
        	mDrawerList.setItemChecked( j , false);            
         }	
        setTitle(mDrawerTitle);	
    }
    
    
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

	public Usuario getUsuarioLogado() {
		// TODO Auto-generated method stub
		return usuarioLogado;
	}
}
